package com.lv.mall.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 17324
 */

@Data
public class UserLoginVo implements Serializable {

    private String account;
    private String password;

}
