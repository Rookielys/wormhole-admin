package com.haha.wormholeadmin.mapper;

import com.haha.wormholeadmin.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2020-07-13
 */
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
    int selectCount();
}
