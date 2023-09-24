package com.biu.controller;

import com.biu.enums.ResponseEnum;
import com.biu.response.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 徐志斌
 * @Date: 2023/9/24 9:16
 * @Version 1.0
 * @Description: UserController
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public R login() {
        return R.out(ResponseEnum.SUCCESS, null);
    }

    @GetMapping("/test")
    public R test() {
        return R.out(ResponseEnum.SUCCESS, "测试成功");
    }
}
