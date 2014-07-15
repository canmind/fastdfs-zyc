package cn.me.fdfs.action;

import cn.me.fdfs.service.UserService;
import cn.me.fdfs.vo.Message;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: devuser
 * Date: 12-8-20
 * Time: 下午3:49
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/main")
public class MainAction {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView("main/index.jsp");
        return mv;
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "main/login.jsp";
    }
    @ResponseBody
    @RequestMapping("/loginDo")
    public Message loginDo(String username,String password,HttpServletRequest request, HttpServletResponse response) throws IOException, MyException {
      boolean  res= userService.login(username,password);
        if(res){
           HttpSession session= request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("userpower",userService.findByName(username).getPower());
            //response.sendRedirect("index.shtml");
            Message message=new Message("200","登录成功","","","","");
            return message;
        }else{
           // response.sendRedirect("loginNot.shtml");
            Message message=new Message("300","用户名或密码错误","","","","");
            return message;
        }
    }
   /* @RequestMapping("/loginNot")
    public String loginNot(){
        return "main/loginnot.jsp";
    }*/
    @RequestMapping("/loginout")
    public void loginout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session= request.getSession();
        session.invalidate();
        response.sendRedirect("login.shtml");
    }
}
