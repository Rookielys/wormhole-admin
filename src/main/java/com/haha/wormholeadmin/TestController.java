package com.haha.wormholeadmin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haha.wormholeadmin.entity.SysRoleEntity;
import com.haha.wormholeadmin.mapper.SysRoleDao;
import com.haha.wormholeadmin.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
public class TestController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleDao sysRoleDao;

    @GetMapping("/test")
    public void tst() {
        //throw new NullPointerException();
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        sysRoleEntity.setRoleCode("test");
        sysRoleEntity.setRoleName("");
        System.out.println(sysRoleDao.selectOne(new QueryWrapper<>(sysRoleEntity)));
    }

    @GetMapping("/test2")
    public void tst2() {
        throw new IndexOutOfBoundsException();
    }

    @GetMapping("/test3")
    public void tst3() {
        throw new ExpressionException("");
    }
}
