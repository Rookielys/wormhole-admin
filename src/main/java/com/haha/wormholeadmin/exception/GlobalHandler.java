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
public class GlobalHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public WormholeResponse<String> unauthorized(UnauthorizedException e) {
        log.error("无权限", e);
        return WormholeResponse.buildError("无权限");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WormholeResponse<String> unknownError(Exception e) {
        log.error("未知错误", e);
        return WormholeResponse.buildError("未知错误，请联系管理员");
    }
}
