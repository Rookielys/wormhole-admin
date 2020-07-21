package com.haha.wormholeadmin.controller;

import com.haha.wormholeadmin.entity.SysUserEntity;
import com.haha.wormholeadmin.utils.WormholeResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/sys")
@RestController
public class LoginController {

    @PostMapping("/login")
    public WormholeResponse<String> login(@RequestBody SysUserEntity userEntity) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return WormholeResponse.buildSuccess("已登录");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUsername(), userEntity.getPwd());
        try {
            subject.login(token);
            // 返回token
            return WormholeResponse.buildSuccess("登录成功", null);
        } catch (Exception e) { // 这里异常要细化
            return WormholeResponse.buildError("登陆失败");
        }
    }
}
