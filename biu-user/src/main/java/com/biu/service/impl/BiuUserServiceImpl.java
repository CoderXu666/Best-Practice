package com.biu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biu.enums.ResponseEnum;
import com.biu.exeception.BiuException;
import com.biu.mapper.BiuUserMapper;
import com.biu.pojo.po.BiuUser;
import com.biu.service.BiuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-09-24
 */
@Service
public class BiuUserServiceImpl extends ServiceImpl<BiuUserMapper, BiuUser> implements BiuUserService {
    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * 登录功能
     */
    @Override
    public String login() {
        String accountId = "xuzhibin";
        String passWord = "xuzhibin";

        // 校验账户、密码是否正确（调用UserDetailsService#loadUserByUsername）
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountId, passWord);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new BiuException(ResponseEnum.PASSWORD_ERROR);
        }

        return null;
    }
}
