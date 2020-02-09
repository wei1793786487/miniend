package com.hqgml.small.mapper;

import com.hqgml.small.pojo.MiniUser;
import org.apache.ibatis.annotations.Param;import tk.mybatis.mapper.common.Mapper;

/**
 * @author Devil
 * @date 2020/2/7 21:39
 */
public interface MiniUserMapper extends Mapper<MiniUser> {
    MiniUser selectOneByOpenid(@Param("openid") String openid);
}