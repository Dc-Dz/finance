package com.qianxia.finance.mapper;

import com.qianxia.finance.domain.Admin;

import java.util.List;

public interface AdminMapper {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    List<Admin> queryAdminByUsername(String username);
}
