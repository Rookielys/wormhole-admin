package com.haha.wormholeadmin.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CaptchaException extends RuntimeException {

    @Setter
    @Getter
    private String code;

    public CaptchaException(String message, String code) {
        super(message);
        this.code = code;
    }
}
