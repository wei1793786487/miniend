package com.hqgml.small.service;

import com.hqgml.small.pojo.LoginToken;
import com.hqgml.small.pojo.MiniUser;

/**
 * @author Devil
 * @date 2020/2/6 12:58
 */
public interface MiniUserService {
    LoginToken getUserInfo(String code);

    int insert(MiniUser miniUser);

    MiniUser selectByOpenId(String openid);

    void updatePid(String opneid,String pid);

}



