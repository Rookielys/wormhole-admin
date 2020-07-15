package com.haha.wormholeadmin.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Message hander() {
        Message message = new Message();
        message.setCode("001");
        message.setMsg("空指针");
        return message;
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public Message hander2() {
        Message message = new Message();
        message.setCode("002");
        message.setMsg("找不到文件");
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Message hander3() {
        Message message = new Message();
        message.setCode("003");
        message.setMsg("未知错误");
        return message;
    }
}

@Data
class Message {
    private String code;
    private String msg;
}
