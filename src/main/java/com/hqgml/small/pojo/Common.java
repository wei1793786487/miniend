package com.hqgml.small.pojo;

import lombok.Data;

/**
 * @author Devil
 * @date 2020/2/9 22:14
 */
@Data
public class Common {

    {
        this.code=200;
        this.timestamp=System.currentTimeMillis();
    }
    private Integer code;
    private Long timestamp;

    private Object  data;
}
