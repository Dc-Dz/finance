package com.qianxia.finance.domain;

import java.io.Serializable;

/**
 * 角色权限表
 */
public class RolePermission implements Serializable{

    private Integer id;  // 主键
    private Integer roleId;  // 角色id
    private Integer permissionId;  // 权限id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
