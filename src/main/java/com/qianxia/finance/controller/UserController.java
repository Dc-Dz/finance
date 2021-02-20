package com.qianxia.finance.controller;

import com.qianxia.finance.common.Result;
import com.qianxia.finance.domain.User;
import com.qianxia.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到个人信息页面 (用户)
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/user/personal/toPersonal.html")
    public String toPersonal(Model model, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        User user = userService.queryUserById(loginUser.getId());
        model.addAttribute("user",user);

        model.addAttribute("pageTopBarInfo", "个人信息界面");

        return "user/personal/profile";
    }

    @PutMapping("/user/updateUserProfile/{userId}")
    public @ResponseBody Result updateUserProfile(@PathVariable("userId") Integer userId, User user, HttpSession session){
        user.setId(userId);
        Integer result = userService.updateUser(user);
        if(result == 1){
            // 当前登录用户信息改变时，session中存储的用户信息也要发生改变
            User loginUser = (User) session.getAttribute("loginUser");
            if (null != loginUser && userId == loginUser.getId()){
               session.setAttribute("loginUser",userService.queryUserById(userId));
            }
            return Result.success();
        }
        return Result.error();
    }
}
