package com.haha.wormholeadmin.controller;

import com.haha.wormholeadmin.constant.SystemConstants;
import com.haha.wormholeadmin.entity.SysUserEntity;
import com.haha.wormholeadmin.exception.CaptchaException;
import com.haha.wormholeadmin.utils.WormholeResponse;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;

@Slf4j
@RequestMapping("/sys")
@RestController
public class LoginController {

    @PostMapping("/login")
    public WormholeResponse<String> login(@RequestBody SysUserEntity userEntity) {
        // 用户信息应该密文
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

    @GetMapping("/verificationCode")
    public WormholeResponse<String> getVerificationCode() {
        SpecCaptcha captcha = new SpecCaptcha(130, 48, 4);
        try {
            captcha.setFont(Captcha.FONT_1);
            captcha.setCharType(Captcha.TYPE_DEFAULT);
            String code = captcha.text();
            String base64 = captcha.toBase64();
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(true);
            session.setAttribute(SystemConstants.SESSION_VERIFICATION_CODE, code);
            return WormholeResponse.buildSuccess("验证码获取成功", base64);
        } catch (IOException | FontFormatException e) {
            throw new CaptchaException("获取验证码失败", "001");
        }

    }
}
