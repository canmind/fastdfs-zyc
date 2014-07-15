package cn.me.fdfs.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.me.fdfs.vo.Machine;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devuser
 * Date: 12-8-23
 * Time: 下午3:51
 * To change this template use File | Settings | File Templates.
 */
public class Tools {

    private static final Logger logger = LoggerFactory.getLogger(Tools.class);
    public static List<Machine> machines;
    public static List<String> exeRemoteConsole(String hostname, String username, String password, String cmd) {
        List<String> result = new ArrayList<String>();
        //指明连接主机的IP地址
        Connection conn = new Connection(hostname);
        Session ssh = null;
        try {
            //连接到主机
            conn.connect();
            //使用用户名和密码校验
            boolean isconn = conn.authenticateWithPassword(username, password);
            if (!isconn) {
                logger.error("用户名称或者是密码不正确");
            } else {
                logger.info("已经连接OK");
                ssh = conn.openSession();
                //使用多个命令用分号隔开
//              ssh.execCommand("pwd;cd /tmp;mkdir shb;ls;ps -ef|grep weblogic");
                ssh.execCommand(cmd);
                //只允许使用一行命令，即ssh对象只能使用一次execCommand这个方法，多次使用则会出现异常
//              ssh.execCommand("mkdir hb");
                //将屏幕上的文字全部打印出来
                InputStream is = new StreamGobbler(ssh.getStdout());
                BufferedReader brs = new BufferedReader(new InputStreamReader(is));
                for (String line = brs.readLine(); line != null; line = brs.readLine()) {
                    result.add(line);
                }
            }
            //连接的Session和Connection对象都需要关闭
            if (ssh != null) {
                ssh.close();
            }
            conn.close();
        } catch (IOException e) {
            logger.error("", e);
        }
        return result;
    }
    public static String getRootPath() {
        String classPath = Tools.class.getClassLoader().getResource("/").getPath();
        String rootPath  = "";
        //windows下
        if("\\".equals(File.separator)){
            rootPath  = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("/", "\\");
        }
        //linux下
        if("/".equals(File.separator)){
            rootPath  = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("\\", "/");
        }
        return rootPath;
    }
    public static String getClassPath(){
        String classPath = Tools.class.getClassLoader().getResource("/").getPath();

        //windows下
        if("\\".equals(File.separator)){

            classPath = classPath.replace("/", "\\");
        }
        //linux下
        if("/".equals(File.separator)){

            classPath = classPath.replace("\\", "/");
        }
        return classPath;
    }

    static {
        SAXReader saxReader = new SAXReader();
        try {
            System.out.println(Tools.getClassPath());
            Document document = saxReader.read(Tools.getClassPath() + "config.xml");
            Element root = document.getRootElement();
            machines = new ArrayList<Machine>();
            @SuppressWarnings("unchecked")
            List<Element> elements = root.elements("server");
            for (Element element : elements) {
                Machine machine = new Machine();
                String ip = element.element("ip").getText();
                String username = element.element("username").getText();
                if(element.element("password")!=null){

                    String password = element.element("password").getText();
                    machine.setPassword(password);
                    machine.setConfigType(true);      //用户名密码登录
                }

                if(element.element("ssh")!=null){

                    String ssh = element.element("ssh").getText();
                    machine.setSsh(ssh);
                    machine.setConfigType(false);    //密钥登录
                    int port = Integer.parseInt(element.element("port").getText());
                    machine.setPort(port);
                }
                String logpath = element.element("logpath").getText();
                machine.setIp(ip);
                machine.setUsername(username);

                machine.setLogpath(logpath);

                machines.add(machine);
            }
        } catch (DocumentException e) {
            logger.error("read config.xml error!!!!", e);
        }
    }
}
