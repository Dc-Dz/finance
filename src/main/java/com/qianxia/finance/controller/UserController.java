package com.qianxia.finance.controller;

import com.qianxia.finance.domain.User;
import com.qianxia.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/personal/toPersonal.html")
    public String toPersonal(Model model, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        User user = userService.queryUserById(loginUser.getId());
        model.addAttribute("user",user);

        model.addAttribute("pageTopBarInfo", "个人信息界面");

        return "user/personal/profile";
    }
}
