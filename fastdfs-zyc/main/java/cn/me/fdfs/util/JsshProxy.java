package cn.me.fdfs.util;

import ch.ethz.ssh2.StreamGobbler;
import com.jcraft.jsch.*;

import java.io.*;
import java.util.List;

/**
 * 
 * @author tozhangwj
 *
 */
public class JsshProxy {
	
	public static enum FileType {
        SHELL, CONFIG, LIB, LOG, WAR
    }
	
	public static String ClientPath = "/usr/local/vagent/";
	public static String ClientShellPath = "/usr/local/vagent/bin/";
	public static String ClientLibPath = "/usr/local/vagent/lib/";
	public static String ClientConfPath = "/usr/local/vagent/conf/";
	public static String ClientLogPath = "/usr/local/vagent/log/";
	public static String ClientWarPath = "/usr/local/vagent/war/";
	
	private int authType;//1=passwd,2=id_dsa文件
	
	private String ip;
	private String username;
	private String passwd;
	private int port;
	
	private String dsaPath;
	
	private JSch jsch =new JSch();
    
	
	public JsshProxy(String ip,String username,String passwd,int port){
		this.ip = ip;
		this.username = username;
		this.passwd = passwd;
		this.port = port;
		authType = 1;
	}
	
	public JsshProxy(String ip,String username,int port,String dsaPath) throws JSchException {
		this.ip = ip;
		this.username = username;
		this.port = port;
		this.dsaPath = dsaPath; 
		jsch.addIdentity(dsaPath);
		authType = 2;
	}
	

	public CommandMessage updateFile(FileType type ,File shellFile){
		CommandMessage  cm = new CommandMessage();
		cm.setCmdString("upload "+shellFile.getName());
		long start = System.currentTimeMillis();
		Session session = null;
		ChannelSftp c = null;
		if(shellFile!=null&&shellFile.canRead()&&shellFile.isFile()){
			try {
				session = jsch.getSession(username, ip, port);
				if(authType==1){
					session.setPassword(passwd);
				}

				session.setUserInfo(new SftpUserInfo());
				
				java.util.Properties config = new java.util.Properties(); 
				config.put("StrictHostKeyChecking", "no");  
				session.setConfig(config);
				
				cm.setExecuteDate(System.currentTimeMillis());
				
				session.connect();
				c = (ChannelSftp)session.openChannel("sftp");
				c.connect();
				
				String distpath = "";
				switch (type) {
					case SHELL:
						distpath = ClientShellPath;
						break;
					case CONFIG:
						distpath = ClientConfPath;
						break;
					case LIB:
						distpath = ClientLibPath;
						break;
					case LOG:
						distpath = ClientLogPath;
						break;
					case WAR:
						distpath = ClientWarPath;
						break;
					default:
						break;
			}
				try {
					c.mkdir(ClientPath);
				} catch (SftpException e1) {
					// e1.printStackTrace();
				}
				try {
					c.mkdir(distpath);
				} catch (SftpException e1) {
					// e1.printStackTrace();
				}
	
				
//				List<Object> ls = c.ls("/usr/local/va*");
//				for(Object obj : ls){
//					System.out.println(obj);
//				}
				c.put(shellFile.getAbsolutePath(), distpath+shellFile.getName(), ChannelSftp.OVERWRITE);
				if(type== FileType.SHELL){
					c.chmod(755, distpath+shellFile.getName());
				}

				cm.setType(CommandMessage.MessageType.OK);
				cm.setMessage("更新文件 ( "+shellFile.getAbsolutePath()+" ) 成功");
				
			} catch (JSchException e) {
				cm.setType(CommandMessage.MessageType.CLIENT_ERROR);
				cm.setMessage(e.getMessage());
				e.printStackTrace();
			} catch (SftpException e) {
				cm.setType(CommandMessage.MessageType.CLIENT_ERROR);
				cm.setMessage(e.getMessage());
				e.printStackTrace();
			}finally{
				if(c!=null){
					c.disconnect();
					c.exit();
				}
				if(session!=null){
					session.disconnect();
				}
				cm.setExecuteTime(System.currentTimeMillis()-start);
			}
		}else{
			cm.setType(CommandMessage.MessageType.ERROR);
			cm.setMessage("File error:"+shellFile);
		}
		return cm;
	}
	
	public CommandMessage execute(String shellName){
		CommandMessage  cm = new CommandMessage();
		cm.setCmdString(shellName);
		long start = System.currentTimeMillis();
		Session session = null;
		ChannelExec c = null;
		InputStream in = null;

		try {
			session = jsch.getSession(username, ip, port);
			if(authType==1){
				session.setPassword(passwd);
			}
			
			session.setUserInfo(new SftpUserInfo());
			
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");  
			session.setConfig(config);
			
			cm.setExecuteDate(System.currentTimeMillis());
			
			session.connect();
			
			c = (ChannelExec)session.openChannel("exec");
			c.setCommand(shellName);
			c.setInputStream(null);
			

			in = c.getInputStream();

			c.connect();

            InputStream is = new StreamGobbler( c.getInputStream());
            BufferedReader brs = new BufferedReader(new InputStreamReader(is));

             for (String line = brs.readLine(); line != null; line = brs.readLine()) {

                    cm.getExecuteLines().add(line);
             }
              cm.setType(CommandMessage.MessageType.OK);


			


			
			
		} catch (JSchException e) {
			cm.setType(CommandMessage.MessageType.CLIENT_ERROR);
			cm.setMessage(e.getMessage());
		} catch (IOException e) {
			cm.setType(CommandMessage.MessageType.CLIENT_ERROR);
			cm.setMessage(e.getMessage());
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(c!=null){
				c.disconnect();
			}
			if(session!=null){
				session.disconnect();
			}
			cm.setExecuteTime(System.currentTimeMillis()-start);
		}
		return cm;
	}
	
	
	public CommandMessage executeCommand(String cmdString,List<String> par){
		CommandMessage  cm = new CommandMessage();
		cm.setCmdString(cmdString);
		long start = System.currentTimeMillis();
		Session session = null;
		ChannelExec c = null;
		InputStream in = null;
		ByteArrayOutputStream baos = null;
		try {
			session = jsch.getSession(username, ip, port);
			if(authType==1){
				session.setPassword(passwd);
			}
			
			session.setUserInfo(new SftpUserInfo());
			
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");  
			session.setConfig(config);
			
			cm.setExecuteDate(System.currentTimeMillis());
			
			session.connect(1000);
			
			c = (ChannelExec)session.openChannel("exec");
			c.setCommand(cmdString);

			baos = new ByteArrayOutputStream();
			c.setOutputStream(baos);
			c.setErrStream(baos);
			in = c.getInputStream();
			StringBuilder sb = new StringBuilder();
			c.connect();
			if(par!=null){
				for(String p : par){
					c.getOutputStream().write((p+"\n").getBytes());
					c.getOutputStream().flush();
					try{Thread.sleep(500);}catch(Exception ee){}
				}
			}

			byte[] tmp=new byte[1024];
		      while(true){
		        while(in.available()>0){
		          int i=in.read(tmp, 0, 1024);
		          if(i<0)break;
		          	sb.append(new String(tmp, 0, i));
		          	//System.out.println(sb);
		        }
		        if(c.isClosed()){
		          //System.out.println("exit-status: "+c.getExitStatus());
		          break;
		        }
		        try{Thread.sleep(200);}catch(Exception ee){}
		      }

		   String message = baos.toString("utf-8");
		    if(message.length()>0){
		    	cm.setType(CommandMessage.MessageType.CLIENT_ERROR);
				cm.setMessage(message);
		    }else{
		    	cm.setType(CommandMessage.MessageType.OK);
				cm.setMessage(sb.toString());
		    }
			
			
		} catch (JSchException e) {
			cm.setType(CommandMessage.MessageType.CLIENT_ERROR);
			cm.setMessage(e.getMessage());
		} catch (IOException e) {
			cm.setType(CommandMessage.MessageType.CLIENT_ERROR);
			cm.setMessage(e.getMessage());
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(baos!=null){
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(c!=null){
				c.disconnect();
			}
			if(session!=null){
				session.disconnect();
			}
			cm.setExecuteTime(System.currentTimeMillis()-start);
		}
		return cm;
	}
	
	public CommandMessage updatePasswd(String newPasswd){
		CommandMessage cm;
		if(authType==1){
			cm = executeCommand("echo "+username+":"+passwd+" | chpasswd",null);
			this.passwd = newPasswd;
		}else{
			cm = new CommandMessage();
			cm.setExecuteDate(System.currentTimeMillis());
			cm.setCmdString("passwd");
			cm.setMessage("登录类型位RSA登录");
			cm.setType(CommandMessage.MessageType.ERROR);
		}
		return cm;
	}
	
	
	//get

	public String getIp() {
		return ip;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswd() {
		return passwd;
	}

	public int getPort() {
		return port;
	}
	
	private static class SftpUserInfo implements UserInfo,
			UIKeyboardInteractive {
		public String getPassword() {
			return passwd;
		}
		
		public void setPassword(String passwd) {
			this.passwd = passwd;
		}
		
		public boolean promptYesNo(String str) {
			return true;
		}
		
		String passwd;
		
		public String getPassphrase() {
			return null;
		}
		
		public boolean promptPassphrase(String message) {
			return true;
		}
		
		public boolean promptPassword(String message) {
			return true;
		}
		
		public void showMessage(String message) {
		
		}
		
		public String[] promptKeyboardInteractive(String destination,
				String name, String instruction, String[] prompt, boolean[] echo) {
		
			return null; // cancel
		}
	}

}
