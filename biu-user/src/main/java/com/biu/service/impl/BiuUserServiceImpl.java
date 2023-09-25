package com.biu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biu.enums.ResponseEnum;
import com.biu.exeception.BiuException;
import com.biu.mapper.BiuUserMapper;
import com.biu.pojo.dto.LoginUserDTO;
import com.biu.pojo.po.BiuUser;
import com.biu.pojo.security.LoginUser;
import com.biu.service.BiuUserService;
import com.biu.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * 登录功能
     */
    @Override
    public String login(LoginUserDTO userDTO) {
        String accountId = userDTO.getAccountId();
        String passWord = userDTO.getPassWord();

        // 校验账户、密码是否正确（调用UserDetailsService#loadUserByUsername）
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountId, passWord);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new BiuException(ResponseEnum.PASSWORD_ERROR);
        }

        // 获取登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        // 查询一下用户信息
        // 这里查询的意义是：store层会通过Spring Cache将用户信息进行缓存
        redisTemplate.opsForValue().set(accountId, loginUser.getUserInfo());

        // 返回Token
        return JWTUtil.generateToken(loginUser.getUserInfo().getAccountId());
    }

    /**
     * 退出登录
     */
    @Override
    public Boolean logout() {
        SecurityContext context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        String jsonString = JSONObject.toJSONString(principal);
        BiuUser userInfo = JSON.parseObject(jsonString, BiuUser.class);
        return redisTemplate.delete(userInfo.getAccountId());
    }
}
