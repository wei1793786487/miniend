package com.hqgml.small.pojo;

import lombok.Data;

/**
 * @author Devil
 * @date 2020/2/8 21:07
 */
@Data
public class LoginToken {
    {
        this.code=200;
        this.timestamp=System.currentTimeMillis();
    }
    private Integer code;
    private Long timestamp;
    private Boolean isband;
    private String token;

}
