package com.hqgml.small.controller;


import cn.hutool.system.UserInfo;
import com.hqgml.small.exception.ExceptionEnums;
import com.hqgml.small.exception.XxException;
import com.hqgml.small.pojo.JwtProperties;
import com.hqgml.small.pojo.LoginToken;
import com.hqgml.small.pojo.MiniUser;
import com.hqgml.small.pojo.Persons;
import com.hqgml.small.service.MiniUserService;
import com.hqgml.small.service.PersonsService;
import com.hqgml.small.utlis.JwtUtils;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



/**
 * @author Devil
 * @date 2020/2/6 12:58
 */
@ResponseBody
@Controller
@RequestMapping("miniUser")
@EnableConfigurationProperties(JwtProperties.class)
@Slf4j
public class MiniUserController {


    @Autowired
    private MiniUserService miniUserService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private PersonsService personsService;
    /**
     * 登录
     * @param code
     * @param
     * @return
     */
    @GetMapping("login")
    public ResponseEntity<LoginToken> login(@RequestParam String code) {
        LoginToken userInfo = miniUserService.getUserInfo(code);
       return ResponseEntity.ok(userInfo);
    }

    @GetMapping
    public ResponseEntity<Boolean> checkToken(@RequestHeader(value = "token")String token){
        boolean isBand=false;
        try {
            MiniUser infoFromToken = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
            Integer persondId = infoFromToken.getPId();
            if (persondId!=null&& 0 != persondId){
                Persons persons = personsService.selectByid(persondId);
                if (persons!=null){
                    isBand=true;
                }
            }
            log.info("认证成功");
        } catch (Exception e) {
            System.out.println("token校验失败");
            log.info("认证失败");
            throw new XxException(ExceptionEnums.AUTH_ERROR);
        }
        return ResponseEntity.ok(isBand);
    }




}
