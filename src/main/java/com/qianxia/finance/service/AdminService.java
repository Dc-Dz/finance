package com.qianxia.finance.service;

import com.qianxia.finance.domain.Admin;

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
}
