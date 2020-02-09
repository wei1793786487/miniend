package com.hqgml.small.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hqgml.small.mapper.MeetingMapper;
import com.hqgml.small.service.MeetingService;
/**
*@author Devil
*@date  2020/2/9 21:22
*/
@Service
public class MeetingServiceImpl implements MeetingService{

    @Resource
    private MeetingMapper meetingMapper;

}
