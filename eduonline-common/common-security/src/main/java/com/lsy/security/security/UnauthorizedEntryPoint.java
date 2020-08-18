package com.lsy.security.security;

import com.lsy.common.utils.ResponseUtil;
import com.lsy.common.utils.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : Lo Shu-ngan
 * @Classname UnauthorizedEntryPoint
 * @Description 未授权的统一处理方式
 * @Date 2020/08/17 16:31
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        ResponseUtil.out(response, Result.error());
    }
}

