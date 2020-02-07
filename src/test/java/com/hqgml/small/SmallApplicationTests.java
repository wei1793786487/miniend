package com.hqgml.small;

import com.hqgml.small.mapper.PersonsMapper;
import com.hqgml.small.pojo.JwtProperties;
import com.hqgml.small.pojo.MiniUser;
import com.hqgml.small.pojo.Persons;
import com.hqgml.small.service.PersonsService;
import com.hqgml.small.service.impl.MiniUserServiceImpl;
import com.hqgml.small.service.impl.PersonsServiceImpl;
import com.hqgml.small.utlis.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SmallApplicationTests {

    @Autowired
   private MiniUserServiceImpl miniUserService;

    @Autowired
    private PersonsMapper personsService;

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    void contextLoads() {

    }

    @Test
    void contextLoad() {
        MiniUser d = miniUserService.selectByOpenId("o8o2v4k6ePvEwXJma99DFatP6hR4");
        System.out.println(d);
    }
  @Test
    void  demoauth() throws Exception {
        String cookie="eyJhbGciOiJSUzI1NiJ9.eyJpZCI6NiwidXNlcm5hbWUiOiJvOG8ydjRrNmVQdkV3WEptYTk5REZhdFA2aFI0Iiwib3BlbmlkIjoic0E1aTBnQVJiRHlXVEZBMXNPSkZGUT09IiwiZXhwIjoxNTgxMTA5NjI4fQ.Mli3N-4M2gD3nALSUYC_G3qWGCwoIFVvMqC6roIHWHgMZzTeg8NlAY19tP4UQAhFutFWrp3z_3yrxerSENfQRWgYYhonIwzIsBt-SySuvJ66O8X43sncGtpPAuEgr3tE22EtYmEuDa27K5XRk3ozPaKzdxNmMT3zI-ALTfqSBas";
      MiniUser infoFromToken = JwtUtils.getInfoFromToken(cookie, this.jwtProperties.getPublicKey());
      System.out.println(infoFromToken);
  }
}
