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

    /**
     * 更新用户信息(用户自己更改)
     * @param id
     * @param user
     * @param session
     * @return
     */
    @PutMapping("/user/updateUserProfile/{userId}")
    public @ResponseBody Result updateUserProfile(@PathVariable("userId") Integer id, User user, HttpSession session){
        user.setId(id);
        Integer result = userService.updateUser(user);
        if(result == 1){
            // 当前登录用户信息改变时，session中存储的用户信息也要发生改变
            User loginUser = (User) session.getAttribute("loginUser");
            if (null != loginUser && id == loginUser.getId()){
               session.setAttribute("loginUser",userService.queryUserById(id));
            }
            return Result.success();
        }
        return Result.error();
    }

    /**
     * 用户强制下线(管理员更改用户信息)
     * @param id
     * @param session
     * @return
     */
    @PutMapping("/user/updateUserStatus/{id}")
    public @ResponseBody Result updateUserStatus(@PathVariable("id") Integer id,HttpSession session){
        User user = userService.queryUserById(id);
        user.setStatus(0);
        Integer result = userService.updateUserStatus(user);
        if (result == 1){
            // 当前登录用户强制下线
            session.removeAttribute("loginUser");
            return Result.success();
        }
        return Result.error();
    }
}
