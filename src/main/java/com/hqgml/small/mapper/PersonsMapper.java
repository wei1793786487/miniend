package com.hqgml.small.mapper;

import com.hqgml.small.pojo.Persons;
import org.apache.ibatis.annotations.Param;import tk.mybatis.mapper.common.Mapper;

/**
 * @author Devil
 * @date 2020/2/7 21:37
 */
public interface PersonsMapper extends Mapper<Persons> {
    Persons selectOneById(@Param("id")Integer id);

   Persons selectOneByPersonName(@Param("personName")String personName);


}