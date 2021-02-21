package com.qianxia.finance.domain;

import java.io.Serializable;

/**
 * 权限表
 */
public class Permissions implements Serializable {

    private static final long serialVersionUID = 5929121133430246317L;

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