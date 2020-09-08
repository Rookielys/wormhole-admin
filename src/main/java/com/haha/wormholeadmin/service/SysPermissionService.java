package com.haha.wormholeadmin.service;

import com.haha.wormholeadmin.entity.SysPermissionEntity;
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
public interface SysPermissionService extends IService<SysPermissionEntity> {
    List<String> getLoginUserPermission(String username);
}
