package com.biu.pojo.security;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.biu.pojo.po.BiuUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 徐志斌
 * @Date: 2023/9/24 10:17
 * @Version 1.0
 * @Description: LoginUser
 */
@Data
@AllArgsConstructor
public class LoginUser implements UserDetails {
    private BiuUser userInfo;
    private List<String> permissions;
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;


    /**
     * 授权信息
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isNotEmpty(authorities)) {
            return authorities;
        }
        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    /**
     * 获取密码
     */
    @Override
    public String getPassword() {
        return userInfo.getPassWord();
    }

    /**
     * 获取账号
     */
    @Override
    public String getUsername() {
        return userInfo.getAccountId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
