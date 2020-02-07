package com.hqgml.small.exception;

import lombok.Data;

/**
 * @author Devil
 * @date 2019/12/18 21:06
 * 异常处理
 */
@Data
public class ExceptionResult {
    private int code;
    private String message;
    private long timestamp;

    public ExceptionResult(ExceptionEnums e) {
        this.code = e.getCode();
        this.message = e.getMsg();
        this.timestamp = System.currentTimeMillis();
    }

    public ExceptionResult(int code, String msg) {
        this.code = code;
        this.message = msg;
        this.timestamp = System.currentTimeMillis();
    }
}
