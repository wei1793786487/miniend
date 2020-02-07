package com.hqgml.small.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Devil
 * @date 2019/12/18 21:03
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ExceptionEnums {
    AUTH_ERROR(400, "认证失败"),
    RESOUT_NOT_ONE(500, "结果集不唯一"),
    OPENID_ERROR(500, "结果集不唯一"),
    CODE_ERROR(400, "code非法");

    private int code;
    private String Msg;
}
