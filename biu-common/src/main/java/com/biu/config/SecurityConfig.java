package com.biu.config;

import com.biu.filter.TokenAuthenticationFilter;
import com.biu.handler.AccessDeniedPermHandler;
import com.biu.handler.AuthenticationEntryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author 徐志斌
 * @Date: 2023/9/18 22:42
 * @Version 1.0
 * @Description: SpringSecurity配置类
 * ---------------------------------------------------------------------
 * SpringSecurity过滤器链大致流程（默认16个Filter）：
 * 1. TokenAuthenticationFilter：处理 Token
 * 2. UsernamePasswordAuthenticationFilter：认证、授权
 * 3. ExceptionTranslationFilter：异常过滤器，用来处理在认证授权过程中抛出的异常
 * 4. FilterSecurityInterceptor：权限过滤器, 基本位于过滤链的最底部
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启注解权限 @PreAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;
    @Autowired
    private AuthenticationEntryHandler authHandler;
    @Autowired
    private AccessDeniedPermHandler permHandler;

    /**
     * SpringSecurity相关配置
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 1.禁用CSRF和Session（使用JWT）
        httpSecurity
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用HttpSession

        // 2.请求授权
        httpSecurity
                .authorizeRequests()
                // 匿名访问：未登录可访问
                .antMatchers("/user/login","/container/test")
                .anonymous()
                // 任何状态都可以访问
                .antMatchers("/user/test")
                .permitAll()
                // OPTIONS请求放行
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                // 认证后可访问
                .anyRequest()
                .authenticated();

        // 3.禁用缓存
        httpSecurity.headers().cacheControl();

        // 4.添加JWT Token过滤器
        httpSecurity.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 5.配置异常处理器
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(permHandler)
                .authenticationEntryPoint(authHandler);

        // 6.SpringSecurity跨域配置
        httpSecurity.cors();
    }

    /**
     * BCryptPasswordEncoder：算法bcrypt对密码加密、解密（替代AES）
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 处理登录认证
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    // 测试
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passWord = encoder.encode("xuzhibin");
        System.out.println(passWord);
    }
}

