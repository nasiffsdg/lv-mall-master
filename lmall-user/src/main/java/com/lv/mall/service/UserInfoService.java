package com.lv.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lv.mall.entity.UserInfo;
import com.lv.mall.vo.UserLoginVo;
import com.lv.mall.vo.UserRegisterVo;

/**
* @author 17324
* @description 针对表【lv_user_info】的数据库操作Service
* @createDate 2023-03-01 22:43:41
*/
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 用户注册
     * @param registerVo
     * @return
     */
    Boolean registerByVo(UserRegisterVo registerVo);

    UserInfo login(UserLoginVo loginVo);
}
