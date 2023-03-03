package com.lv.mall.exception;

/**
 * @author 17324
 */
public class PhoneExistException extends RuntimeException{
    public PhoneExistException() {
        super("手机号存在异常");
    }
}
