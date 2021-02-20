package com.qianxia.finance.service;

import com.qianxia.finance.domain.User;

public interface UserService {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User queryUserByUsername(String username);

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
