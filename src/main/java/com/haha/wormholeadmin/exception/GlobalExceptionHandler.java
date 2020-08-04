package com.haha.wormholeadmin.exception;

import com.haha.wormholeadmin.vo.WormholeResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 无权限
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public WormholeResponse<String> error(UnauthorizedException e) {
        log.error("无权限", e);
        return WormholeResponse.buildError("无权限");
    }

    /**
     * 验证码获取失败
     * @param e
     * @return
     */
    @ExceptionHandler(CreateCaptchaException.class)
    @ResponseBody
    public WormholeResponse<String> error(CreateCaptchaException e) {
        log.error(e.getMessage(), e);
        return WormholeResponse.buildError(e.getMessage());
    }

    /**
     * 验证码校验失败
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalCaptchaException.class)
    @ResponseBody
    public WormholeResponse<String> error(IllegalCaptchaException e) {
        log.error(e.getMessage(), e);
        return WormholeResponse.buildError(e.getMessage());
    }

    /**
     * 参数校验失败
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public WormholeResponse<String> error(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : allErrors) {
            stringBuilder.append(error.getDefaultMessage());
        }
        return WormholeResponse.buildError(stringBuilder.toString());
    }

    /**
     * 其它未知错误
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WormholeResponse<String> error(Exception e) {
        log.error("未知错误", e);
        return WormholeResponse.buildError("未知错误，请联系管理员");
    }
}
