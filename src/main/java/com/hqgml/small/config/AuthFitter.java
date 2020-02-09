package com.hqgml.small.config;


import com.alibaba.fastjson.JSON;
import com.hqgml.small.exception.ExceptionEnums;
import com.hqgml.small.exception.ExceptionResult;
import com.hqgml.small.exception.XxException;
import com.hqgml.small.pojo.JwtProperties;
import com.hqgml.small.pojo.MiniUser;
import com.hqgml.small.utlis.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.spec.XPathType;
import java.io.IOException;

/**
 * @author Devil
 * @date 2020/2/7 22:17
 * 验证token
 */
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "AuthFitter")
public class AuthFitter implements Filter {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) (servletRequest);
        HttpServletResponse response = (HttpServletResponse) (servletResponse);
        String requestUrl = request.getRequestURI();
        if (requestUrl.contains("login")) {
            //包含直接过滤掉
            filterChain.doFilter(servletRequest, servletResponse);
            log.info("登录不用校验");
        }else {
            String token = request.getHeader("token");
            if (token!=null){
                try {
                    MiniUser miniUser = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());

                    String newtoken = JwtUtils.generateToken(miniUser, jwtProperties.getPrivateKey(), jwtProperties.getExpire());

                    response.setHeader("token",newtoken);

                    filterChain.doFilter(servletRequest, servletResponse);

                } catch (Exception e) {
                    e.printStackTrace();
                    ExceptionResult result=new ExceptionResult(1000,"认证失效");
                    response.setContentType("text/json;charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(result));
                }
            }else {
                ExceptionResult result=new ExceptionResult(1000,"认证失效");
                response.setContentType("text/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(result));
            }

        }
    }

    @Override
    public void destroy() {

    }
}
