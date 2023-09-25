package com.biu.controller;


import com.biu.enums.ResponseEnum;
import com.biu.pojo.dto.LoginUserDTO;
import com.biu.response.R;
import com.biu.service.BiuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public R logout() {
        userService.logout();
        return R.out(ResponseEnum.SUCCESS, null);
    }

    // 测试
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('test333')")
    public R test() {
        return R.out(ResponseEnum.SUCCESS, null);
    }
}

