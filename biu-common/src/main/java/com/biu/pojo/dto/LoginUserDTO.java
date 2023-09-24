package com.biu.pojo.dto;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2023/9/24 17:25
 * @Version 1.0
 * @Description: LoginUserDTO
 */
@Data
public class LoginUserDTO {
    private Long id;
    private String accountId;
    private String passWord;
    private String captcha;
}
