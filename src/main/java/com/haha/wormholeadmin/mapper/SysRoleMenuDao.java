package com.haha.wormholeadmin.mapper;

import com.haha.wormholeadmin.entity.SysMenuEntity;
import com.haha.wormholeadmin.entity.SysRoleMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2020-07-13
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {
    List<SysMenuEntity> selectUserMenu(String user);
}
