package com.qianxia.finance.mapper;

import com.qianxia.finance.domain.Admin;
import com.qianxia.finance.domain.Permissions;

import java.util.List;

public interface AdminMapper {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    List<Admin> queryAdminByUsername(String username);

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
     * 根据角色id
     * @param id
     * @return
     */
    List<Permissions> queryPermissionsByRoleId(Integer id);

    /**
     * 根据id查单条
     * @param id
     * @return
     */
    Admin queryAdminById(Integer id);
}
