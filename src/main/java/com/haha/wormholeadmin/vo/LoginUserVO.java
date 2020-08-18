package com.haha.wormholeadmin.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginUserVO {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String pwd;
    private String key;
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;
    private Boolean rememberMe;
}
