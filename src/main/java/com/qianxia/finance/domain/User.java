package com.qianxia.finance.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 用户类
 */
public class User implements Serializable{

    private static final long serialVersionUID = -1477313142479404093L;

    private Integer id;  // 主键

    private String username;  // 用户名

    private String realName;  // 真实姓名

    private String password;  // 密码

    private String IDCard;  // 身份证号码

    private String phone;  // 手机号

    private String email;  // 邮箱

    private Integer paypwd;  // 交易密码

    private String salt;  // 随机盐

    private Integer status;  // // 登录状态 0 - 离线，1 - 在线

    private String reputation;  // 用户信誉

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPaypwd() {
        return paypwd;
    }

    public void setPaypwd(Integer paypwd) {
        this.paypwd = paypwd;
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

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}