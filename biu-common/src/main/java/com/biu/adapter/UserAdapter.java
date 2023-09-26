package com.biu.adapter;

import com.biu.pojo.dto.RegisterUserDTO;
import com.biu.pojo.po.BiuUser;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-26  15:48
 * @Description: 适配器模式——用户信息
 * @Version: 1.0
 */
public class UserAdapter {
    public static BiuUser buildUserPO(RegisterUserDTO userDTO) {
        return BiuUser.builder()
                .accountId(userDTO.getAccountId())
                .passWord(userDTO.getPassWord())
                .build();
    }
}
