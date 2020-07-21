package com.haha.wormholeadmin.realms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haha.wormholeadmin.entity.SysUserEntity;
import com.haha.wormholeadmin.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
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
                userEntity.getPwd(), ByteSource.Util.bytes(userEntity.getSalt()), getName());
    }
}
