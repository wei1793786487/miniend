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
    /**
     * 1000 token错误
     */
    AUTH_ERROR(1000, "认证失败"),
    UPDATA_ERROR(500, "更新异常"),
    RESOUT_NOT_ONE(500, "结果集不唯一"),
    OPENID_ERROR(500, "opneid错误"),
    MEETING_NOT_FIND(400, "会议没有找到"),
    NOT_DAND(400,"没有绑定"),
    PSERSON_NOt_FIND(400,"人员没找到"),
    PHONE_ERROR(400, "联系方式不匹配"),
    CODE_ERROR(400, "code非法");

    private int code;
    private String Msg;
}
