package com.haha.wormholeadmin.controller;

import com.haha.wormholeadmin.constant.SystemConstants;
import com.haha.wormholeadmin.exception.CreateCaptchaException;
import com.haha.wormholeadmin.exception.IllegalCaptchaException;
import com.haha.wormholeadmin.vo.WormholeResponse;
import com.haha.wormholeadmin.vo.LoginUserVO;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Slf4j
@RestController
public class LoginController {

    @PostMapping("/login")
    public WormholeResponse<String> login(@RequestBody LoginUserVO loginUserVO) {
        // 用户信息应该密文
//        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            return WormholeResponse.buildSuccess("已登录");
//        }
//        UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUsername(), userEntity.getPwd());
        try {
            //subject.login(token);
            // 返回token
            return WormholeResponse.buildSuccess("登录成功");
        } catch (Exception e) { // 这里异常要细化
            return WormholeResponse.buildError("登陆失败");
        }
    }

    @GetMapping("/verificationCode")
    public WormholeResponse<String> getVerificationCode(HttpServletResponse response) {
        // 禁止浏览器缓存
        response.setHeader("Pragma", "No-Cache");
        response.setHeader("Cache-Control", "No-Cache");
        response.setDateHeader("Expires", 0);
        SpecCaptcha captcha = new SpecCaptcha(98, 38, 4);
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
            throw new CreateCaptchaException("获取验证码失败");
        }
    }

    /**
     * 弃用
     */
    @Deprecated
    @PostMapping("/captchaError")
    public void captchaError() {
        throw new IllegalCaptchaException("验证码错误");
    }
}
