package com.haha.wormholeadmin.exception;

import com.haha.wormholeadmin.utils.WormholeResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public WormholeResponse<String> error(UnauthorizedException e) {
        log.error("无权限", e);
        return WormholeResponse.buildError("无权限");
    }

    @ExceptionHandler(CaptchaException.class)
    @ResponseBody
    public WormholeResponse<String> error(CaptchaException e) {
        log.error(e.getMessage(), e);
        return WormholeResponse.buildError(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WormholeResponse<String> error(Exception e) {
        log.error("未知错误", e);
        return WormholeResponse.buildError("未知错误，请联系管理员");
    }
}
