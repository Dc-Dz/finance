package com.qianxia.finance.domain;

import java.util.List;

/**
 * 管理员类
 */
public class Admin {

    private Integer id;  // 主键id
    private String username;  // 管理员用户名
    private String password;  // 登录密码
    private String salt;  // 随机盐
    private Integer status;  // 登录状态 0 - 离线，1 - 在线
    private List<Role> roles;  // 角色集合

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
