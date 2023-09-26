package com.biu.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.biu.pojo.po.BiuUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-09-24
 */
public interface BiuUserStore extends IService<BiuUser> {
    BiuUser getUserByAccountId(String accountId);

    Boolean saveUser(BiuUser user);
}
