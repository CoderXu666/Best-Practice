package com.biu.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biu.mapper.BiuUserMapper;
import com.biu.pojo.po.BiuUser;
import com.biu.store.BiuUserStore;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-09-24
 */
@Service
public class BiuUserStoreImpl extends ServiceImpl<BiuUserMapper, BiuUser> implements BiuUserStore {
    /**
     * 根据id查询用户信息
     */
    @Override
    @Cacheable(value = "biu_user", key = "#p0")
    public BiuUser getUserByAccountId(String accountId) {
        QueryWrapper<BiuUser> wrapper = new QueryWrapper<>();
        wrapper.eq("account_id", accountId);
        return this.getOne(wrapper);
    }
}
