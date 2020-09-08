package com.haha.wormholeadmin.service;

import com.haha.wormholeadmin.entity.SysMenuEntity;
import com.haha.wormholeadmin.entity.SysRoleMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lian
 * @since 2020-07-13
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {
    List<SysMenuEntity> getUserMenu(String user);
}
