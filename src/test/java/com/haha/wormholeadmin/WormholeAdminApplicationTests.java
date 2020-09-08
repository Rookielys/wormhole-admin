package com.haha.wormholeadmin;

import com.haha.wormholeadmin.entity.SysRoleEntity;
import com.haha.wormholeadmin.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class WormholeAdminApplicationTests {
    @Autowired
    private SysRoleService sysRoleService;

    @Test
    void contextLoads() throws SQLException {
//        SysRoleEntity entity = new SysRoleEntity();
//        entity.setRoleCode("user:query");
//        entity.setRoleName("用户查询");
//        sysRoleService.save(entity);
    }

}
