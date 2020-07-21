package com.haha.wormholeadmin.utils;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class WormholeResponse<T> {
    private T data;
    private String message;
    private boolean status;

    public static <K> WormholeResponse<K> buildSuccess(String message, K data) {
        return new WormholeResponse<K>().setData(data)
                .setMessage(message).setStatus(true);
    }

    public static <K> WormholeResponse<K> buildSuccess(String message) {
        return new WormholeResponse<K>().setMessage(message).setStatus(true);
    }

    public static <K> WormholeResponse<K> buildError(String message, K data) {
        return new WormholeResponse<K>().setData(data)
                .setMessage(message).setStatus(false);
    }

    public static <K> WormholeResponse<K> buildError(String message) {
        return new WormholeResponse<K>().setMessage(message).setStatus(false);
    }
}
