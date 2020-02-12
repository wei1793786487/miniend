package com.hqgml.small.controller;

import com.hqgml.small.exception.ExceptionEnums;
import com.hqgml.small.exception.XxException;
import com.hqgml.small.pojo.Common;
import com.hqgml.small.pojo.JwtProperties;
import com.hqgml.small.pojo.Meeting;
import com.hqgml.small.pojo.MiniUser;
import com.hqgml.small.service.MeetingService;
import com.hqgml.small.utlis.JwtUtils;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Devil
 * @date 2020/2/9 22:07
 */
@RestController
@RequestMapping("meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @Autowired
    JwtProperties jwtProperties;

    /**
     * 查找所绑定的用户的会议
     *
     * @param token
     * @return
     */
    @GetMapping
    public ResponseEntity<Common> selectByMiNi(@RequestHeader(value = "token") String token) throws Exception {
        try {
            MiniUser miniUser = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
            if (miniUser.getPId()==0){
                throw new XxException(ExceptionEnums.NOT_DAND);
            }
                List<Meeting> meetings = meetingService.selectByMiNiId(miniUser.getPId());
                Common common = new Common();
                common.setData(meetings);
                return ResponseEntity.ok(common);
        } catch (Exception e) {
            if (e instanceof XxException){
                throw e;
            }else {
                throw new XxException(ExceptionEnums.AUTH_ERROR);
            }
        }

    }

    /**
     * 查找所绑定的用户的会议
     *
     * @param
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Common> selectMeeting(@PathVariable("id") Integer id) {
        Meeting meeting = meetingService.selectOneById(id);
        Common common = new Common();
        common.setData(meeting);
        return ResponseEntity.ok(common);
    }
}
