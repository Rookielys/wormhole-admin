package com.haha.wormholeadmin.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserVO {
    private String username;
    private String pwd;
    private String verifyCode;
    private String rememberMe;
}
