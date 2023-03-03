package com.lv.mall.to;

import com.lv.mall.constant.UserLevel;
import com.lv.mall.entity.UserInfo;
import lombok.Data;

/**
 * @author 17324
 */
@Data
public class UserInfoTo {
    private UserInfo userInfo;
    private UserLevel userLevel;
}
