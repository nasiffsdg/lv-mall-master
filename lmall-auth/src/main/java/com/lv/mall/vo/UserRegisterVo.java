package com.lv.mall.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author 17324
 */
@Data
public class UserRegisterVo implements Serializable {

    @NotEmpty(message = "用户名不能为空")
    @Length(min = 6, max = 18,message = "用户名必须是6-18")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 18,message = "用户名必须是6-18")
    private String passWord;

    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^[1][0-9]{11}$", message = "手机号格式不正确")
    private String phone;

    @NotEmpty(message = "验证码不能为空")
    private String coed;
}
