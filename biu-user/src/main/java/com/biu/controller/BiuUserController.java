package com.biu.controller;


import com.biu.enums.ResponseEnum;
import com.biu.response.R;
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
    @GetMapping("/login")
    public R login() {
        return R.out(ResponseEnum.SUCCESS, null);
    }
}

