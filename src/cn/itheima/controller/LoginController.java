package cn.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * author: zzw5005
 * date: 2018/6/24 15:29
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    //跳转到登陆页面
    @RequestMapping("/login")
    public String login() throws Exception{
        return "login";
    }


    @RequestMapping("/submit")
    public String submit(String username, String pwd, HttpServletRequest request)
         throws Exception{
        HttpSession session = request.getSession();
        //判断用户密码的正确性，如果正确则将登录信息放入session中
        //这里简写，真正的项目中需要去数据库中校验用户名和密码
        if(username != null && username != ""){
            session.setAttribute("username",username);
        }

        //跳转到列表页面
        return "redirect:/items/list";
    }

}
