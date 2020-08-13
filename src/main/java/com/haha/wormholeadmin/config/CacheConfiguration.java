package com.haha.wormholeadmin.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Scheduler;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {

    /**
     * 此处配置Caffeine对象之后配置文件的好像失效了
     *
     * @return
     */
    @Bean
    public CacheManagerCustomizer<CaffeineCacheManager> cacheManagerCustomizer() {
        return (cacheManager) -> {
//            cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).removalListener((k, v, cause) -> {
//                        System.out.println(k + " ----> " + v);
//                    }));
        };
    }
}
