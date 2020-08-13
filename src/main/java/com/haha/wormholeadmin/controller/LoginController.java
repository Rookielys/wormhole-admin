package com.haha.wormholeadmin.controller;

import cn.hutool.core.lang.UUID;
import com.haha.wormholeadmin.exception.CreateCaptchaException;
import com.haha.wormholeadmin.util.JwtUtil;
import com.haha.wormholeadmin.vo.CaptchaResponseVO;
import com.haha.wormholeadmin.vo.WormholeResponse;
import com.haha.wormholeadmin.vo.LoginUserVO;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private CacheManager cacheManager;

    @PostMapping("/login")
    public WormholeResponse<String> login(@Validated LoginUserVO loginUserVO, HttpServletResponse response) throws IOException {
        // 用户信息应该密文
        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            return WormholeResponse.buildSuccess("已登录");
//        }
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginUserVO.getUsername(), loginUserVO.getPwd());
        try {
            subject.login(usernamePasswordToken);
            // 返回token
            Map<String, Object> map = new HashMap<>();
            map.put("username", loginUserVO.getUsername());
            String token = JwtUtil.createToken(map);
            return WormholeResponse.buildSuccess(null, token);
        } catch (UnknownAccountException e) {
            return WormholeResponse.buildError("用户不存在");
        } catch (CredentialsException e) {
            return WormholeResponse.buildError("密码错误");
        } catch (Exception e) {
            return WormholeResponse.buildError("登陆失败");
        }
    }

    /**
     * 生成验证码
     * 无session状态下需要额外生成一个id来标识此验证码
     *
     * @param response
     * @return
     */
    @GetMapping("/verificationCode")
    public WormholeResponse<CaptchaResponseVO> getVerificationCode(HttpServletResponse response) {
        // 禁止浏览器缓存
        response.setHeader("Pragma", "No-Cache");
        response.setHeader("Cache-Control", "No-Cache");
        response.setDateHeader("Expires", 0);
        try {
            SpecCaptcha captcha = new SpecCaptcha(98, 38, 4);
            captcha.setFont(Captcha.FONT_1);
            captcha.setCharType(Captcha.TYPE_DEFAULT);
            String code = captcha.text();
            String base64 = captcha.toBase64();
            CaptchaResponseVO captchaResponseVO = new CaptchaResponseVO();
            String key = UUID.randomUUID().toString(true);
            captchaResponseVO.setKey(key).setText(base64);
            Cache cache = cacheManager.getCache("captcha");
            cache.put(key, code.toLowerCase());
            return WormholeResponse.buildSuccess(null, captchaResponseVO);
        } catch (Exception e) {
            log.error("验证码获取失败", e);
            throw new CreateCaptchaException("获取验证码失败");
        }
    }

}
