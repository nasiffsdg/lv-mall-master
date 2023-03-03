package com.lv.mall.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 17324
 */
@Configuration
public class ESConfig {


    public static final RequestOptions COMMON_OPPTIONS;
    static {
        COMMON_OPPTIONS = RequestOptions.DEFAULT.toBuilder().build();
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("39.101.78.97", 9200, "http")));
    }

}
