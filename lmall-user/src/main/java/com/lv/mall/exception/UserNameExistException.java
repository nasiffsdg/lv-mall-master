package com.lv.mall.exception;

/**
 * @author 17324
 */
public class UserNameExistException extends RuntimeException{
    public UserNameExistException() {
        super("用户名存在异常");
    }
}
