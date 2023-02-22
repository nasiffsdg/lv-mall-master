package com.lv.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 17324
 */
@Configuration
@MapperScan({ "com.lv.mall.**.mapper" })
public class MybatisConfig {

}
