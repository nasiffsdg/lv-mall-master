package com.lv.mall;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author 17324
 */
@EnableFeignClients
@SpringBootApplication
@EnableRedisHttpSession
@EnableRabbit
public class OrderApplication {
    public static void main(String[] args) {

        SpringApplication.run(OrderApplication.class,args);
    }
}
