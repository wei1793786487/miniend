package com.hqgml.small.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.hqgml.small.pojo.Meeting;
import tk.mybatis.mapper.common.Mapper;

/**
*@author Devil
*@date  2020/2/9 21:22
*/
public interface MeetingMapper extends Mapper<Meeting> {
    List<Meeting> selectMeetingbymini(@Param("id")Integer id);

     Meeting selectById(@Param("id")Integer id);




}