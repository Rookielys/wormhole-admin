package com.haha.wormholeadmin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class CaptchaResponseVO {
    private String key;
    private String text;
}
