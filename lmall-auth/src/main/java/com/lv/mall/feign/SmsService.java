package com.lv.mall.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 17324
 */
@FeignClient("biz-server")
public interface SmsService {

    /**
     *
     * @param code
     * @param phoneNumber
     * @return
     */
    @GetMapping("/biz/sms/{phoneNumber}/{code}")
    public ResponseEntity<Boolean> smsSend(@PathVariable String code, @PathVariable String phoneNumber);
}
