package com.hqgml.small.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqgml.small.exception.ExceptionEnums;
import com.hqgml.small.exception.XxException;
import com.hqgml.small.pojo.JwtProperties;
import com.hqgml.small.pojo.LoginToken;
import com.hqgml.small.pojo.MiniUser;
import com.hqgml.small.utlis.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.hqgml.small.mapper.MiniUserMapper;
import com.hqgml.small.service.MiniUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Devil
 * @date 2020/2/6 12:58
 */
@Service
@Slf4j
public class MiniUserServiceImpl implements MiniUserService {


    private static final String WX_AUTO_URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String APPID = "wxa8d655672c5b4e6b";
    private static final String SECUERT = "c1384aa5b2949189243ce41f6372a37d";
    private static final String GRANDT_TYPE = "authorization_code";
    private static final String ERRCODE = "errcode";


    @Resource
    private MiniUserMapper miniUserMapper;

    @Autowired
    JwtProperties jwtProperties;


    @Override
    public LoginToken getUserInfo(String code) {
        JSONObject jsonObject;
        Map<String, Object> parms = new HashMap<>();
        parms.put("appid", APPID);
        parms.put("secret", SECUERT);
        parms.put("grant_type", GRANDT_TYPE);
        parms.put("js_code", code);

        String response = HttpUtil.get(WX_AUTO_URL, parms);

        try {
            jsonObject = JSON.parseObject(response);
            if (jsonObject.getInteger(ERRCODE) == null) {
                String openid = jsonObject.getString("openid");
                LoginToken loginToken=new LoginToken();
                if (openid != null) {
                    //看看数据库是不是有存在的，如果有的话就返回查询到的token如果没有的话插入 然后返回新的
                    MiniUser miniUser1 = selectByOpenId(openid);
                    if (miniUser1 == null) {
                        MiniUser miniUser = new MiniUser(openid);
                        int id = insert(miniUser);
                        miniUser.setId(id);
                        log.info("用户不存在新建");
                        String token = JwtUtils.generateToken(miniUser, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
                         //用户刚刚创建的时候坑定是不存在绑定的
                        loginToken.setIsband(false);
                        loginToken.setToken(token);
                        return  loginToken;
                    } else {

                        String token = JwtUtils.generateToken(miniUser1, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
                        loginToken.setToken(token);

                        log.info("用户存在使用原始的");
                       if (miniUser1.getPId()==null){
                           loginToken.setIsband(false);
                           log.info("用户没有绑定");
                       }else {
                           loginToken.setIsband(true);
                           log.info("用户绑定了");
                       }
                        return loginToken;
                    }
                } else {
                    throw new XxException(ExceptionEnums.OPENID_ERROR);
                }


            } else {
                throw new XxException(ExceptionEnums.CODE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XxException(ExceptionEnums.AUTH_ERROR);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(MiniUser miniUser) {
        miniUserMapper.insert(miniUser);
        return miniUser.getId();
    }


    @Override
    public MiniUser selectByOpenId(String openid) {
        return miniUserMapper.selectOneByOpenid(openid);

    }


}



