package com.lv.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 17324
 */
@EnableFeignClients
@SpringBootApplication
public class StockApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplication.class, args);
    }
}
