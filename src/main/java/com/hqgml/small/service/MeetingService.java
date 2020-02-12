package com.hqgml.small.service;

import com.hqgml.small.pojo.Meeting;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
*@author Devil
*@date  2020/2/9 21:22
*/
public interface MeetingService{

 /**
  * 根据的是mid与pid查询属于这个用户的会议
  * @param id
  * @return
  */
 List<Meeting> selectByMiNiId(Integer id);

 /**
  * 根据id查询
  * @param id
  * @return
  */
 Meeting selectOneById(Integer id);
}
