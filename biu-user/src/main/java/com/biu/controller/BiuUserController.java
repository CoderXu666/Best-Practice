package com.biu.controller;


import com.biu.enums.ResponseEnum;
import com.biu.response.R;
import com.biu.service.BiuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     */
    @GetMapping("/login")
    public R login() {
        userService.login();
        return R.out(ResponseEnum.SUCCESS, null);
    }
}

