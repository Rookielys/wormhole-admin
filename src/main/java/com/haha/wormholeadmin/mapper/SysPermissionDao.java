package com.haha.wormholeadmin.mapper;

import com.haha.wormholeadmin.entity.SysPermissionEntity;
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
public interface SysPermissionDao extends BaseMapper<SysPermissionEntity> {
    List<String> getLoginUserPermission(String username);
}
