package com.lv.mall.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 17324
 */
@RestController
@RequestMapping("/biz")
public class SmsController {

    @GetMapping("/sms/{phoneNumber}/{code}")
    public ResponseEntity<Boolean> smsSend(@PathVariable String code, @PathVariable String phoneNumber){
        return ResponseEntity.ok(true);
    }
}