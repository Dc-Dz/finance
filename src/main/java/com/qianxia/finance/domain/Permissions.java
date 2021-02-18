package com.qianxia.finance.domain;

/**
 * 权限表
 */
public class Permissions {
    private Integer id;  // 主键

    private String permissions;  // 权限

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}