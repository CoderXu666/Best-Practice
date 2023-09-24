package com.biu.controller;


import com.biu.enums.ResponseEnum;
import com.biu.pojo.dto.LoginUserDTO;
import com.biu.response.R;
import com.biu.service.BiuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-09-24
 */
@RestController
@RequestMapping("/user")
public class BiuUserController {
    @Autowired
    private BiuUserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录
     * ----------------------------------------------------
     * SpringSecurity版本登录：
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginUserDTO userDTO) {
        String token = userService.login(userDTO);
        return R.out(ResponseEnum.SUCCESS, token);
    }

    // 测试
    @GetMapping("/test")
    public R test() {
        redisTemplate.opsForValue().set("111", "test");
        return R.out(ResponseEnum.SUCCESS, null);
    }
}

