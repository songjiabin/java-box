package com.x.y.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class loginController {

    @RequestMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("passWord") String passWord,
                        Model model, HttpSession session) {
        // 具体的业务,登录成功跳转到 dashboard 页面
        if (!StringUtils.isEmpty(userName) && "123456".equals(passWord)) {
            session.setAttribute("loginUser", userName);
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "用户名或者密码错误");
            return "index";
        }
    }
}
