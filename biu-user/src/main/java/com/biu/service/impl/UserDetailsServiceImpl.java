package com.biu.service.impl;

import com.biu.pojo.po.BiuUser;
import com.biu.pojo.security.LoginUser;
import com.biu.store.BiuUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 徐志斌
 * @Date: 2023/9/18 22:25
 * @Version 1.0
 * @Description: 自定义UserDetailsService实现类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private BiuUserStore userStore;

    /**
     * 校验账号密码是否正确
     * ---------------------------------------
     * 触发时机：authenticationManager.authenticate(authenticationToken)
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 查询用户信息
        BiuUser userInfo = userStore.getUserByAccountId(userName);

        // TODO 查询用户的权限信息
        List<String> permissions = new ArrayList<>(Arrays.asList("test", "admin"));


        // TODO 数据封装成UserDetails返回
        return new LoginUser(userInfo, permissions, null);
    }
}
