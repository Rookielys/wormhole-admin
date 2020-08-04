package com.haha.wormholeadmin.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CreateCaptchaException extends RuntimeException {

    public CreateCaptchaException(String message) {
        super(message);
    }
}
