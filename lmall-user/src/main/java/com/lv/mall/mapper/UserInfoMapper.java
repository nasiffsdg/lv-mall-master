package com.lv.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lv.mall.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 17324
* @description 针对表【lv_user_info】的数据库操作Mapper
* @createDate 2023-03-01 22:43:41
* @Entity com.lv.mall.entity.UserInfo 
*/
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    List<UserInfo> checkPhone(@Param("phone") String phone);

    List<UserInfo> checkUserName(@Param("userName") String userName);
}




