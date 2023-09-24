package com.biu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biu.enums.ResponseEnum;
import com.biu.exeception.BiuException;
import com.biu.mapper.BiuUserMapper;
import com.biu.pojo.dto.LoginUserDTO;
import com.biu.pojo.po.BiuUser;
import com.biu.pojo.security.LoginUser;
import com.biu.service.BiuUserService;
import com.biu.store.BiuUserStore;
import com.biu.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    private BiuUserStore userStore;
    @Autowired
    private RedisTemplate redisTemplate;
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
        LoginUser userInfo = (LoginUser) authenticate.getPrincipal();

        // 查询一下用户信息
        // 这里查询的意义是：store层会通过Spring Cache将用户信息进行缓存
//        userStore.getUserByAccountId(accountId);
        redisTemplate.opsForValue().set(accountId, userInfo);

        // 返回Token
        return JWTUtil.generateToken(userInfo.getUserInfo().getAccountId());
    }
}
