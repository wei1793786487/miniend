package com.hqgml.small.mapper;

import com.hqgml.small.pojo.MiniUser;
import org.apache.ibatis.annotations.Param;import tk.mybatis.mapper.common.Mapper;

/**
 * @author Devil
 * @date 2020/2/6 23:29
 */
public interface MiniUserMapper extends Mapper<MiniUser> {
    MiniUser selectOneByOpenid(@Param("openid")String openid);


}