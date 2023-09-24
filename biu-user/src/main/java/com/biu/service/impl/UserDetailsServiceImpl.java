//package com.biu.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.biu.pojo.security.LoginUser;
//import com.biu.store.BiuUserStore;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
///**
// * @Author 徐志斌
// * @Date: 2023/9/18 22:25
// * @Version 1.0
// * @Description: 自定义UserDetailsService实现类
// */
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private BiuUserStore userStore;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        // 查询用户信息
//        QueryWrapper<BingoUser> wrapper = new QueryWrapper<>();
//        wrapper.eq("account_id", userName);
//        BingoUser userInfo = userStore.getOne(wrapper);
//        if (Objects.isNull(userInfo)) {
//            throw new RuntimeException("用户名、密码错误");
//        }
//
//        // TODO 查询用户的权限信息
//
//
//        // TODO 数据封装成UserDetails返回
//        return new LoginUser(userInfo);
//    }
//}