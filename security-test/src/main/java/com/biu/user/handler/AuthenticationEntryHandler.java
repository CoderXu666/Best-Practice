package com.biu.user.handler;

import com.alibaba.fastjson.JSON;
import com.biu.enums.ResponseEnum;
import com.biu.response.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-26  13:03
 * @Description: 认证异常处理器
 * @Version: 1.0
 */
@Component
public class AuthenticationEntryHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R r = R.out(ResponseEnum.FAIL, "用户认证失败");
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(r));
    }
}
