package cn.me.fdfs.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: LZS
 * Date: 11-12-9
 * Time: 上午10:04
 */
public class SysInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(SysInterceptor.class);
    public  static String  contentPath = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getContextPath();
        String prePath = request.getScheme()+"://"+request.getServerName();
        String basePath = prePath +":"+request.getServerPort()+path+"/";
        request.setAttribute("basePath",basePath);
        contentPath = path;
        StringBuffer requestpath=request.getRequestURL();

       if(requestpath.indexOf("login")==-1){
           Object name=request.getSession().getAttribute("username") ;
           if(name==null){
               response.sendRedirect(basePath+"/main/login.shtml");
           }
       }
		//buildMail.send("service@vivame.cn","VivaMe维我", "5515068@qq.com", "", "系统日志", request.getRemoteAddr() +" " +handler.getClass().getName(), null);
        System.out.println(request.getRemoteAddr() +" " +handler.getClass().getName() );
        return true;
    }
}