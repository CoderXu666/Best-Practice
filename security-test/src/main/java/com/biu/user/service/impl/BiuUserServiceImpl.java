package com.biu.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biu.adapter.UserAdapter;
import com.biu.enums.ResponseEnum;
import com.biu.exeception.BiuException;
import com.biu.user.mapper.BiuUserMapper;
import com.biu.pojo.dto.LoginUserDTO;
import com.biu.pojo.dto.RegisterUserDTO;
import com.biu.pojo.po.BiuUser;
import com.biu.pojo.security.LoginUser;
import com.biu.user.service.BiuUserService;
import com.biu.user.store.BiuUserStore;
import com.biu.utils.JWTUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BiuUserStore userStore;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * 登录功能
     */
    @Override
    public String login(LoginUserDTO userDTO) {
        // 校验账户认证是否正确（调用UserDetailsService#loadUserByUsername）
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getAccountId(), userDTO.getPassWord());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new BiuException(ResponseEnum.PASSWORD_ERROR);
        }
        // 获取登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 查询一下用户信息
        redisTemplate.opsForValue().set(userDTO.getAccountId(), loginUser);
        // 返回Token
        return JWTUtil.generateToken(loginUser.getUserInfo().getAccountId());
    }

    /**
     * 注册
     */
    @Override
    public Boolean register(RegisterUserDTO userDTO) {
        // 判断账号是否注册过
        BiuUser userInfo = userStore.getUserByAccountId(userDTO.getAccountId());
        if (ObjectUtils.isNotEmpty(userInfo)) {
            throw new BiuException(ResponseEnum.USER_EXIST);
        }
        // 保存账号信息
        userDTO.setPassWord(passwordEncoder.encode(userDTO.getPassWord()));
        BiuUser userPO = UserAdapter.buildUserPO(userDTO);
        return userStore.saveUser(userPO);
    }

    /**
     * 退出登录
     */
    @Override
    public Boolean logout() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String jsonString = JSONObject.toJSONString(principal);
        LoginUser userInfo = JSON.parseObject(jsonString, LoginUser.class);
        return redisTemplate.delete(userInfo.getUserInfo().getAccountId());
    }
}
