package com.lv.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 17324
 */
@Configuration
public class ThreadPoolConfiguration {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigurationProperties configurationProperties){

        return new ThreadPoolExecutor(
                configurationProperties.getCoreSize(),
                configurationProperties.getMaxSize(),
                configurationProperties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
