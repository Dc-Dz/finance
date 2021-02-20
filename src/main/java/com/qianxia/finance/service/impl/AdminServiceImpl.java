package com.qianxia.finance.service.impl;

import com.qianxia.finance.domain.Admin;
import com.qianxia.finance.domain.Permissions;
import com.qianxia.finance.mapper.AdminMapper;
import com.qianxia.finance.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Integer updateAdminStatus(Admin admin) {
        return adminMapper.updateAdminStatus(admin);
    }

    @Override
    public Admin queryRoleByRoles(String username) {
        return adminMapper.queryRoleByRoles(username);
    }

    @Override
    public List<Permissions> queryPermissionsByRoleId(Integer id) {
        return adminMapper.queryPermissionsByRoleId(id);
    }

    @Transactional
    @Override
    public Admin queryAdminById(Integer id) {

        Admin admin = adminMapper.queryAdminById(id);
        admin.setStatus(0);
        Integer result = adminMapper.updateAdminStatus(admin);
        if (result == 1){
            return admin;
        }else {
            return null;
        }

    }
}
