package com.qianxia.finance.mapper;

import com.qianxia.finance.domain.Permissions;
import com.qianxia.finance.domain.User;

import java.util.List;

public interface UserMapper {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    List<User> queryUserByUsername(String username);

    /**
     * 注册
     * @param user
     * @return
     */
    Integer register(User user);

    /**
     * 修改登录状态
     * @param user
     * @return
     */
    Integer updateUserStatus(User user);

    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    User queryRoleByRoles(String username);

    /**
     * 根据角色id
     * @param id
     * @return
     */
    List<Permissions> queryPermissionsByRoleId(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryUserAll();

    /**
     * 根据id查单条
     * @param id
     * @return
     */
    User queryAdminById(Integer id);
}