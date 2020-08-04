package com.haha.wormholeadmin.vo;

import com.haha.wormholeadmin.constant.SystemConstants;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class WormholeResponse<T> {
    private T data;
    private String message;
    private boolean status;
    private String code;

    public static <K> WormholeResponse<K> buildSuccess(String message, K data) {
        return new WormholeResponse<K>().setData(data)
                .setMessage(message).setStatus(true).setCode(SystemConstants.RESPONSE_SUCCESS_CODE);
    }

    public static <K> WormholeResponse<K> buildSuccess(String message) {
        return new WormholeResponse<K>().setMessage(message).setStatus(true).setCode(SystemConstants.RESPONSE_SUCCESS_CODE);
    }

    public static <K> WormholeResponse<K> buildError(String message, K data) {
        return new WormholeResponse<K>().setData(data)
                .setMessage(message).setStatus(false).setCode(SystemConstants.RESPONSE_ERROR_CODE);
    }

    public static <K> WormholeResponse<K> buildError(String message) {
        return new WormholeResponse<K>().setMessage(message).setStatus(false).setCode(SystemConstants.RESPONSE_ERROR_CODE);
    }
}
