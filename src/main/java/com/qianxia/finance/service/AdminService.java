package com.qianxia.finance.service;

import com.qianxia.finance.domain.Admin;
import com.qianxia.finance.domain.Permissions;

import java.util.List;

public interface AdminService {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Admin queryAdminByUsername(String username);

    /**
     * 更改登录状态
     * @param admin
     * @return
     */
    Integer updateAdminStatus(Admin admin);

    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    Admin queryRoleByRoles(String username);

    /**
     * 根据角色id查询权限集合
     * @param id
     * @return
     */
    List<Permissions> queryPermissionsByRoleId(Integer id);
}
