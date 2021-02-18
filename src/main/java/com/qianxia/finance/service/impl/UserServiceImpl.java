package com.qianxia.finance.service.impl;

import com.qianxia.finance.common.Constant;
import com.qianxia.finance.domain.User;
import com.qianxia.finance.mapper.UserMapper;
import com.qianxia.finance.service.UserService;
import com.qianxia.finance.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByUsername(String username) {

        User user = new User();
        if (!username.equalsIgnoreCase(user.getUsername())){
            user.setUsername(username);
        }

        List<User> userList = userMapper.queryUserByUsername(username);
        if ("[]".equals(userList.toString())){
            return null;
        }else {
            return userList.get(0);
        }

    }

    @Override
    public Integer register(User user) {
        // 1.生成随机盐
        String salt = SaltUtil.getSalt(Constant.SALT_LEN);
        // 2.将随机盐保存到数据库
        user.setSalt(salt);
        // 3.对明文进行MD5 + 随机盐 + hash散列加密
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, Constant.MD5_HASH);
        user.setPassword(md5Hash.toHex());
        return userMapper.register(user);
    }
}
