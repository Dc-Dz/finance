package com.qianxia.finance.domain;

/**
 * 管理员角色
 */
public class AdminRole {
    private Integer id;  // 主键

    private Integer adminId;  // 管理员id

    private Integer roleId;  // 角色id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}