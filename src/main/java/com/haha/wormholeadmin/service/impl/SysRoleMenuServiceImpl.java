package com.haha.wormholeadmin.service.impl;

import com.haha.wormholeadmin.entity.SysMenuEntity;
import com.haha.wormholeadmin.entity.SysRoleMenuEntity;
import com.haha.wormholeadmin.mapper.SysRoleMenuDao;
import com.haha.wormholeadmin.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    @Override
    public List<SysMenuEntity> getUserMenu(String user) {
        if (StringUtils.isEmpty(user)) {
            return new ArrayList<>();
        }
        List<SysMenuEntity> all = this.baseMapper.selectUserMenu(user);
        // 将menu整理成树
        return this.baseMapper.selectUserMenu(user);
    }

    public void setChildren() {

    }
}
