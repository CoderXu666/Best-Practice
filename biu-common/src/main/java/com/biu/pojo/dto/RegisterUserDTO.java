package com.biu.pojo.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-26  15:44
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class RegisterUserDTO {
    private Long id;
    private String accountId;
    private String passWord;
    private String captcha;
}
