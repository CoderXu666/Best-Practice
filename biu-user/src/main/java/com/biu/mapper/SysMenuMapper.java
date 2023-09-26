package com.biu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biu.pojo.po.SysMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author 徐志斌
 * @since 2023-09-26
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<String> getPermsByUserId(Long userId);
}
