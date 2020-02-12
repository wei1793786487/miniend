package com.hqgml.small.service.impl;

import com.hqgml.small.exception.ExceptionEnums;
import com.hqgml.small.exception.XxException;
import com.hqgml.small.pojo.Meeting;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hqgml.small.mapper.MeetingMapper;
import com.hqgml.small.service.MeetingService;
import org.springframework.web.context.request.NativeWebRequest;

import java.nio.channels.NonWritableChannelException;
import java.util.List;

/**
*@author Devil
*@date  2020/2/9 21:22
*/
@Service
public class MeetingServiceImpl implements MeetingService{

    @Resource
    private MeetingMapper meetingMapper;

    @Override
    public List<Meeting> selectByMiNiId(Integer id) {
        //TODO这里可能需要分页· 但是我懒没加
        List<Meeting> meetings = meetingMapper.selectMeetingbymini(id);
        if (meetings.size()==0){
            throw new XxException(ExceptionEnums.MEETING_NOT_FIND);
        }
        return meetings;
    }

    @Override
    public Meeting selectOneById(Integer id) {

        Meeting meeting = meetingMapper.selectById(id);
        if (meeting==null){
            throw new XxException(ExceptionEnums.MEETING_NOT_FIND);
        }
        return meeting;
    }
}
