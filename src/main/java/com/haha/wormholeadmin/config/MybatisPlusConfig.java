package com.haha.wormholeadmin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public MetaObjectHandler myMetaObjectHandler() {
        return new DateTimeMetaObjectHanlder();
    }
}
