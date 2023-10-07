package com.biu.service.impl;

import com.biu.mapper.SysMenuMapper;
import com.biu.store.BiuUserStore;
import com.biu.pojo.po.BiuUser;
import com.biu.pojo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private SysMenuMapper menuMapper;

    /**
     * 校验账号密码，封装权限
     * 触发时机：authenticationManager.authenticate(authenticationToken)
     */
    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        // 查询用户信息
        BiuUser userInfo = userStore.getUserByAccountId(accountId);

        // 查询用户的权限信息
        List<String> permissions = menuMapper.getPermsByUserId(userInfo.getId());

        // 封装LoginUser（认证信息 + 权限信息）
        return new LoginUser(userInfo, permissions, null);
    }
}
