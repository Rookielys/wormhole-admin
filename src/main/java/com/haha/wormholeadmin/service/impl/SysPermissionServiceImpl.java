package com.haha.wormholeadmin.service.impl;

import com.haha.wormholeadmin.entity.SysPermissionEntity;
import com.haha.wormholeadmin.mapper.SysPermissionDao;
import com.haha.wormholeadmin.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lian
 * @since 2020-07-13
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermissionEntity> implements SysPermissionService {

    @Override
    public List<String> getLoginUserPermission(String username) {
        return this.baseMapper.getLoginUserPermission(username);
    }
}
