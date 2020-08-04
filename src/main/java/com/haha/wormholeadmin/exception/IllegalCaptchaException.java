package com.haha.wormholeadmin.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IllegalCaptchaException extends RuntimeException {
    public IllegalCaptchaException(String message) {
        super(message);
    }
}
