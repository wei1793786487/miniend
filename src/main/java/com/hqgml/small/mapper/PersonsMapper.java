package com.hqgml.small.mapper;
import org.apache.ibatis.annotations.Param;

import com.hqgml.small.pojo.Persons;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Devil
 * @date 2020/2/6 20:49
 */
public interface PersonsMapper extends Mapper<Persons> {
    Persons selectoneByWid(@Param("wUid")Integer wUid);


}