package com.lv.mall.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 17324
 */
@ConfigurationProperties(prefix = "lv.thread")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadPoolConfigurationProperties {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
