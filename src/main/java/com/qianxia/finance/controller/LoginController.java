package com.qianxia.finance.controller;

import com.qianxia.finance.common.Result;
import com.qianxia.finance.domain.Admin;
import com.qianxia.finance.domain.User;
import com.qianxia.finance.service.AdminService;
import com.qianxia.finance.service.UserService;
import com.qianxia.finance.utils.SnowflakeIdWorkerUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @GetMapping("/queryByUsername/{username}")
    public @ResponseBody Result queryByUsername(@PathVariable("username") String username){
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
    public @ResponseBody Result login(@RequestParam("username") String username, @RequestParam("password") String password,
                                      Model model){

        User user = userService.queryUserByUsername(username);
        if (null != user){
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            try {
                subject.login(token);
                return Result.success().add("url","/user/index.html");
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                model.addAttribute("message","用户名不存在");
                return Result.error();
            }catch (IncorrectCredentialsException e){
                e.printStackTrace();
                model.addAttribute("message","密码错误!!!");
                return Result.error();
            }
        }

        Admin admin = adminService.queryAdminByUsername(username);
        if (null != admin){
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            try {
                subject.login(token);
                return Result.success().add("url","/admin/index.html");
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                // 用户名错误
                model.addAttribute("message","用户名不存在");
                return Result.error();
            }catch (IncorrectCredentialsException e){
                e.printStackTrace();
                // 密码错误
                model.addAttribute("message","密码错误!!!");
                return Result.error();
            }
        }

        return Result.error();
    }
}
