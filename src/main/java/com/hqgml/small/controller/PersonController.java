package com.hqgml.small.controller;

import com.hqgml.small.exception.ExceptionEnums;
import com.hqgml.small.exception.XxException;
import com.hqgml.small.pojo.Common;
import com.hqgml.small.pojo.JwtProperties;
import com.hqgml.small.pojo.MiniUser;
import com.hqgml.small.pojo.Persons;
import com.hqgml.small.service.MiniUserService;
import com.hqgml.small.service.PersonsService;
import com.hqgml.small.utlis.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Devil
 * @date 2020/2/8 21:05
 */
@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonsService personsService;

    @Autowired
    private MiniUserService miniUserService;

    @Autowired
    JwtProperties jwtProperties;


    @PostMapping
    public ResponseEntity<Common> banding(
            @RequestParam("pname") String personName,
            @RequestParam("phone") String phone,
            @RequestHeader(value = "token") String token
    ) throws Exception {
        //todo 可以判断一下是不是已经绑定过了
        Persons persons = personsService.selectOneByName(personName);
        if (!StringUtils.equals(phone, persons.getPhone())) {
            throw new XxException(ExceptionEnums.PHONE_ERROR);
        }
        MiniUser info = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
        miniUserService.updatePid(info.getOpenid(), persons.getId().toString());
        Common common = new Common();
        common.setData("绑定成功");
        return ResponseEntity.ok(common);
    }
}
