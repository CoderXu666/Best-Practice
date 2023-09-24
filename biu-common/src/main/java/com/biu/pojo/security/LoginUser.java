package com.biu.pojo.security;

import com.biu.pojo.po.BiuUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author 徐志斌
 * @Date: 2023/9/24 10:17
 * @Version 1.0
 * @Description: LoginUser
 */
@AllArgsConstructor
public class LoginUser implements UserDetails {
    private BiuUser userInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassWord();
    }

    @Override
    public String getUsername() {
        return userInfo.getAccountId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
