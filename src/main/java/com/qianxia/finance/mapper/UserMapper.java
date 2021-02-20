package com.qianxia.finance.mapper;

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
}