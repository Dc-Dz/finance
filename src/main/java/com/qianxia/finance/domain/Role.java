package com.qianxia.finance.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 */
public class Role implements Serializable{

    private static final long serialVersionUID = 4392149277673980445L;

    private Integer id;  // 主键

    private String role;  // 角色

    private String description;  // 角色描述

    private List<Permissions> permissions;  // 权限集合

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
}