package com.biu.user.mapper;

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
    /**
     * 根据user_id查询用户权限
     */
    @Select("        SELECT DISTINCT m.perms\n" +
            "        FROM sys_user_role AS ur\n" +
            "                 LEFT JOIN sys_role AS r ON ur.role_id = r.id\n" +
            "                 LEFT JOIN sys_role_menu AS rm ON ur.role_id = rm.role_id\n" +
            "                 LEFT JOIN sys_menu AS m ON m.id = rm.menu_id\n" +
            "        WHERE user_id = #{userId}")
    List<String> getPermsByUserId(Long userId);
}
