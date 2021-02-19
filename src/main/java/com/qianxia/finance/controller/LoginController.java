package com.qianxia.finance.controller;

import com.qianxia.finance.common.Result;
import com.qianxia.finance.domain.Admin;
import com.qianxia.finance.domain.User;
import com.qianxia.finance.service.AdminService;
import com.qianxia.finance.service.UserService;
import com.qianxia.finance.utils.SnowflakeIdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @GetMapping("/queryByUsername/{username}")
    public @ResponseBody
    Result queryByUsername(@PathVariable("username") String username){
        Admin admin = adminService.queryAdminByUsername(username);
        if (null != admin){
            return Result.success();
        }

        User user = userService.queryUserByUsername(username);
        if (null != user){
            return Result.success();
        }
        return Result.error();
    }

    @GetMapping("/register")
    public @ResponseBody Result register(@RequestParam("username") String username, @RequestParam("password") String password){
        User user = new User();
        user.setId(SnowflakeIdWorkerUtil.getUuid());
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(0);
        user.setReputation("良好");
        Integer result = userService.register(user);
        if (result == 1){
            return Result.success().add("url","/");
        }else {
            return Result.error();
        }
    }

    @PostMapping("/toLogin")
    public @ResponseBody Result login(@RequestParam("username") String username, @RequestParam("password") String password){

        return Result.error();
    }
}
