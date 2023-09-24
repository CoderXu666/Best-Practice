package com.biu.filter;

import com.biu.utils.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author 徐志斌
 * @Date: 2023/9/24 18:58
 * @Version 1.0
 * @Description: TokenAuthenticationFilter
 * ----------------------------------------------------
 * 跟传统登陆方式不同，本次登录Token验证使用Filter，而不是拦截器HandlerInterceptor
 * OncePerRequestFilter：保证请求只会经过过滤器一次
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    /**
     * 存入SecurityContextHolder原因：
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取Token
        String token = request.getHeader("token");

        // 判断Token是否存在（这里放行是因为后续还有Filter会处理这里认证状态，不需要在这里抛出异常）
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 解析Token
        Map<String, Object> resultMap = JWTUtil.resolveToken(token);
        String accountId = (String) resultMap.get("account_id");

        // 获取用户信息


        // 存入SecurityContextHolder

        // 放行
        filterChain.doFilter(request, response);
    }
}
