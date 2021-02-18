package com.qianxia.finance.service.impl;

import com.qianxia.finance.domain.Admin;
import com.qianxia.finance.mapper.AdminMapper;
import com.qianxia.finance.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin queryAdminByUsername(String username) {

        Admin admin = new Admin();
        if (!username.equalsIgnoreCase(admin.getUsername())){
            admin.setUsername(username);
        }

        List<Admin> adminList = adminMapper.queryAdminByUsername(username);
        if ("[]".equalsIgnoreCase(adminList.toString())){
            return null;
        }else {
            return adminList.get(0);
        }
    }
}
