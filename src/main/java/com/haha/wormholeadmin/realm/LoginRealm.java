package com.haha.wormholeadmin.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haha.wormholeadmin.entity.SysUserEntity;
import com.haha.wormholeadmin.service.SysPermissionService;
import com.haha.wormholeadmin.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginRealm extends AuthorizingRealm {

    public static final String REALM_NAME = "LoginRealm";

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissionService.getLoginUserPermission(username));
        return authorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", token1.getUsername()).last("limit 1");
        SysUserEntity userEntity = userService.getOne(queryWrapper);
        if (userEntity == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(token1.getUsername(),
                userEntity.getPwd(), ByteSource.Util.bytes(userEntity.getSalt()), REALM_NAME);
    }
}
