package com.biu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.biu.pojo.po.BiuUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-09-24
 */
public interface BiuUserService extends IService<BiuUser> {
    String login();
}
