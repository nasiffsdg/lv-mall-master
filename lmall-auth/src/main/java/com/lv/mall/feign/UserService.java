package com.lv.mall.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.lv.mall.entity.UserInfo;
import com.lv.mall.vo.UserLoginVo;
import com.lv.mall.vo.UserRegisterVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 17324
 */
@FeignClient("user-server")
public interface UserService {


    @PostMapping("/user/userInfo")
    public R insert(@RequestBody UserRegisterVo registerVo);

    @PostMapping("/user/userInfo/login")
    public UserInfo login(@RequestBody UserLoginVo loginVo);
}
