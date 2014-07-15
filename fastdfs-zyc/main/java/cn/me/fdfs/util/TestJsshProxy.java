package cn.me.fdfs.util;


import com.jcraft.jsch.JSchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJsshProxy {
    private static final Logger logger = LoggerFactory
            .getLogger(TestJsshProxy.class);
	/**
	 * @param args
	 * @throws
	 */
	public static void main(String[] args) throws JSchException {

		JsshProxy jssh = new JsshProxy("192.168.0.48","root",22,"/Users/Chen/.ssh/id_rsa");
        CommandMessage cm =  jssh.execute("ps -aux|grep fdfs");
        System.out.println(cm.getExecuteLines().toString());

		System.exit(0);
	}

}
