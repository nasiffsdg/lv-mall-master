package com.lv.mall.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author 17324
 */
@Data
public class UserRegisterVo {
    private String userName;

    private String passWord;

    private String phone;
}
