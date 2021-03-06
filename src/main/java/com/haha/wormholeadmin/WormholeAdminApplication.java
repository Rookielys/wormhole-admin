package com.haha.wormholeadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan({"com.haha.wormholeadmin.mapper"})
public class WormholeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WormholeAdminApplication.class, args);
    }

}
