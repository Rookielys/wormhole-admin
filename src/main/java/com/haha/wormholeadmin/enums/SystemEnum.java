package com.haha.wormholeadmin.enums;

import com.haha.wormholeadmin.constant.SystemConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum SystemEnum {
    LOGIN_TIMEOUT(SystemConstants.LOGIN_TIMEOUT_CODE, "登录超时");

    private String code;
    private String message;
}
