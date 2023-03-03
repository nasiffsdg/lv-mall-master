package com.lv.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lv.mall.UserApplication;
import com.lv.mall.entity.UserInfo;
import com.lv.mall.exception.PhoneExistException;
import com.lv.mall.exception.UserNameExistException;
import com.lv.mall.mapper.UserInfoMapper;
import com.lv.mall.service.UserInfoService;
import com.lv.mall.vo.UserLoginVo;
import com.lv.mall.vo.UserRegisterVo;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
* @author 17324
* @description 针对表【lv_user_info】的数据库操作Service实现
* @createDate 2023-03-01 22:43:41
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService {


    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public Boolean registerByVo(UserRegisterVo registerVo) {

        UserInfo userInfo = new UserInfo();

        // 唯一性校验
        List<UserInfo> userInfos = userInfoMapper.checkPhone(registerVo.getPhone());
        List<UserInfo> userInfos1 = userInfoMapper.checkUserName(registerVo.getUserName());
        if (!(userInfos==null||userInfos.size() == 0)){
            throw new PhoneExistException();
        }
        if (!(userInfos1==null||userInfos1.size() == 0)){
            throw new UserNameExistException();
        }

        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setLevel(1L);
        userInfo.setMobile(registerVo.getPhone());
        userInfo.setStatus(1);
        userInfo.setUsername(registerVo.getUserName());
        // 密码的加密存储
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // spring 把盐放到密码中
        String encode = passwordEncoder.encode(registerVo.getPassWord());
        userInfo.setPassword(encode);

         return this.save(userInfo);
    }

    @Override
    public UserInfo login(UserLoginVo loginVo) {

        // 数据库查询
        UserInfo selectOne = baseMapper.selectOne(new QueryWrapper<UserInfo>().eq("username", loginVo.getAccount()).or().eq("mobile", loginVo.getAccount()));

        if (selectOne == null){
            return null;
        }
        String pwdDb = selectOne.getPassword();
        if (new BCryptPasswordEncoder().matches(loginVo.getPassword(), pwdDb)){
            return selectOne;
        }
        return null;
    }
}




