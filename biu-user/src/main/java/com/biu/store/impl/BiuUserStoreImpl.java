package com.biu.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biu.mapper.BiuUserMapper;
import com.biu.pojo.po.BiuUser;
import com.biu.store.BiuUserStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-09-24
 */
@Service
public class BiuUserStoreImpl extends ServiceImpl<BiuUserMapper, BiuUser> implements BiuUserStore {

}