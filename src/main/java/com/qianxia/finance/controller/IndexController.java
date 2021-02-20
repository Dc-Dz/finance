package com.qianxia.finance.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianxia.finance.domain.Admin;
import com.qianxia.finance.domain.News;
import com.qianxia.finance.domain.User;
import com.qianxia.finance.service.AdminService;
import com.qianxia.finance.service.NewsService;
import com.qianxia.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;

    /**
     * 管理员首页
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/admin/index.html")
    public String toAdminIndex(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                               Model model){
        // 引入PageHelper插件，在查询之前调用startPage方法，传入页码和每页数量
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userService.queryUserAll();

        PageInfo<User> pageInfo = new PageInfo<>(users,5);
        model.addAttribute("userPageInfo",pageInfo);
        model.addAttribute("userList",users);

        model.addAttribute("pageTopBarInfo","系统首页");
        return "admin/main";
    }

    /**
     * 用户首页
     * @param model
     * @return
     */
    @GetMapping("/user/index.html")
    public String toUserIndex(Model model){

        List<News> list = newsService.queryNewsAll();

        model.addAttribute("newsList",list);
        model.addAttribute("pageTopBarInfo","系统首页");

        return "user/main";
    }

    /**
     * 管理员注销(只有正常退出的用户才可以注销)
     * @param logout
     * @param session
     * @return
     */
    @GetMapping("/admin/logout")
    public String toAdminLogout(@RequestParam("logout") String logout, HttpSession session){
        if ("adminLogout".equalsIgnoreCase(logout)){
            Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
            Admin admin = adminService.queryAdminById(loginAdmin.getId());
            session.removeAttribute("loginAdmin");
            System.out.println("logout=>" + admin.getUsername() + "退出了系统");
            return "login";
        }
        return "login";
    }

    /**
     * 用户注销(只有正常退出的用户才可以注销)
     * @param logout
     * @param session
     * @return
     */
    @GetMapping("/user/logout")
    public String toUserLogout(@RequestParam("logout") String logout, HttpSession session){
        if ("userLogout".equalsIgnoreCase(logout)){
            User loginUser = (User) session.getAttribute("loginUser");
            User user = userService.queryUserById(loginUser.getId());
            user.setStatus(0);
            userService.updateUserStatus(user);
            session.removeAttribute("loginUser");
            System.out.println("logout=>" + user.getUsername() + "退出了系统");
            return "login";
        }
        return "login";
    }
}
