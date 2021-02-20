package com.qianxia.finance.common.shiro;

import com.qianxia.finance.domain.Admin;
import com.qianxia.finance.domain.Permissions;
import com.qianxia.finance.domain.User;
import com.qianxia.finance.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义UserRealm类
 */
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权操作");

        // 获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证=>" + primaryPrincipal);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取角色信息
        User user = userService.queryRoleByRoles(primaryPrincipal);
        if (null != user.getRoles()){
            user.getRoles().forEach(role ->{
                info.addRole(role.getRole());

                // 获取权限信息
                List<Permissions> permissionsList = userService.queryPermissionsByRoleId(role.getId());
                if (null != permissionsList){
                    permissionsList.forEach(permissions -> {
                        info.addStringPermission(permissions.getPermissions());
                    });
                }
            });
        }
        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("调用了认证方法");
        // 获取身份信息
        String principal = (String) token.getPrincipal();

        System.out.println("principal = " + principal);

        User user = userService.queryUserByUsername(principal);
        if (null != user){
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            user.setStatus(1);
            Integer result = userService.updateUserStatus(user);
            if (result == 1){
                session.setAttribute("loginUser","user");
                System.out.println(user.getUsername() + "登录了系统");
                return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),"");
            }
        }

        return null;
    }
}
