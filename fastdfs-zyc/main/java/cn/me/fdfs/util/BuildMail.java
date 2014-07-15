/**
 * 
 */
package cn.me.fdfs.util;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Chen
 *
 */
public class BuildMail {
	private MimeMessage mimeMsg; // MIME邮件对象

	private Session session; // 邮件会话对象

	private Properties props; // 系统属性

	private String username = ""; // smtp认证用户名和密码

	private String password = "";

	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象


	public BuildMail() {
		File file  = new File(Thread.currentThread().getContextClassLoader()
				.getResource("application.properties").getFile());
		
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(file);
			 props = new Properties();
			 try {
				props.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		
		}
		props.get("mail.smtp.host");
		createMimeMessage();
	}

	/**
	 * @return boolean
	 */
	public boolean createMimeMessage() {
		try {
			System.out.println("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
		} catch (Exception e) {
			System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}

		System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
			mp = new MimeMultipart();

			return true;
		} catch (Exception e) {
			System.err.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/**
	 * @param need
	 *            boolean
	 */
	public void setNeedAuth(boolean need) {
		System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = System.getProperties();

		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public void setNamePass(String name, String pass) {
		username = name;
		password = pass;
	}

	/**
	 * @param mailSubject
	 *            String
	 * @return boolean
	 */
	public boolean setSubject(String mailSubject) {
		System.out.println("设置邮件主题！");
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件主题发生错误！");
			return false;
		}
	}

	/**
	 * @param mailBody
	 *            String
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=gb2312>" + mailBody, "text/html;charset=GB2312");
			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean addFileAffix(String filename) {

		System.out.println("增加邮件附件：" + filename);

		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(fileds.getName());

			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setFrom(String from,String view) {
		System.out.println("设置发信人！");
		try {
			
			mimeMsg.setFrom(new InternetAddress(from,view,("GB2312")));// 设置发信人
			return true;
		} catch (Exception e) {
			return false;
		}
	}

    public boolean setFromConfig(String view) {
        System.out.println("设置发信人！");
        try {

            mimeMsg.setFrom(new InternetAddress(props.get("mail.smtp.username").toString(),view));// 设置发信人
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setTo(String to) {
		if (to == null)
			return false;

		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean sendout() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			System.out.println("正在发送邮件....");

			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), (String)props.get("mail.smtp.username"),(String)props.get("mail.smtp.password"));
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			System.out.println("发送邮件成功！");
			transport.close();

			return true;
		} catch (Exception e) {
			System.err.println("邮件发送失败！" + e);
			return false;
		}
	}

	public void send(String from,String view,String to,String cc,String title,String body,String object) {
        setNeedAuth(true);

        if (setSubject(title) == false)
            return;
        if (setBody(body) == false)
            return;
        if (setTo(to) == false)
            return;
        if (setFrom(from,view) == false)
            return;
//		if (addFileAffix("c:\\1.JPG") == false)
        if(object!=null){
            if (addFileAffix(object) == false)
                return;}

        if (sendout() == false)
            return;
    }
    public void sendWarning(String view,String to,String title,String body) {
        setNeedAuth(true);

        if (setSubject(title) == false)
            return;
        if (setBody(body) == false)
            return;
        if (setTo(to) == false)
            return;
        if (setFromConfig(view) == false)
            return;
//		if (addFileAffix("c:\\1.JPG") == false)
//        if(object!=null){
//            if (addFileAffix(object) == false)
//                return;}

        if (sendout() == false)
            return;
    }
	  public static void main(String args[]){ 
		  BuildMail buildMail=new BuildMail();
		  
		 // buildMail.send("service@vivame.cn","VivaMe维我", "young_willow@126.com", "", "VivaMe密码重置", "1234", null);
        buildMail.sendWarning("VivaMe维我", "young_willow@126.com", "VivaMe密码重置", "1234");
	  }
}