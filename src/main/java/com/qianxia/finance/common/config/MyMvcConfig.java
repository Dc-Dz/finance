package com.qianxia.finance.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 扩展SpringMVC功能
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 项目启动后，默认跳转到login界面
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/tologin.html").setViewName("login");
        // 跳转到register注册界面
        registry.addViewController("/toregister.html").setViewName("register");

//        registry.addViewController("/admin/index.html").setViewName("admin/main");
//        registry.addViewController("/user/index.html").setViewName("user/main");
    }
}
