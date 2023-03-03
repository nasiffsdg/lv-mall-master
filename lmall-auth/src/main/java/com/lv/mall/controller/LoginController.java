package com.lv.mall.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lv.mall.constant.AuthServerConstant;
import com.lv.mall.entity.UserInfo;
import com.lv.mall.feign.SmsService;
import com.lv.mall.feign.UserService;
import com.lv.mall.vo.UserLoginVo;
import com.lv.mall.vo.UserRegisterVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 17324
 */
@RestController()
@RequestMapping("auth/login")
public class LoginController {


    @Resource
    SmsService smsService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserService userService;

    @RequestMapping("/sms/sendCode/{phone}")
    public R sendCode(@PathVariable String phone){
        // 防止刷验证码 同一个手机号
        String r_code = stringRedisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE + phone);
        if (!StringUtils.isEmpty(r_code)){
            long time = System.currentTimeMillis() - Long.parseLong(r_code.split("_")[1]);
            if (time < 60000){
                return R.failed("60秒内不能再发");
            }
        }

        String code = UUID.randomUUID().toString().substring(0, 5);
        smsService.smsSend(phone, code);
        stringRedisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE+phone, code+"_"+System.currentTimeMillis(), 10, TimeUnit.MINUTES);
        return R.ok(code);
    }

    @PostMapping("/register")
    public R register(@RequestBody @Valid UserRegisterVo vo, BindingResult result){

        if (result.hasErrors()){
            return R.failed("你输入的数据有问题");
        }
        String r_code = stringRedisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE + vo.getPhone());
        if (!StringUtils.isEmpty(r_code) &&r_code.split("_")[0].equals(vo.getCoed())){
            stringRedisTemplate.delete(AuthServerConstant.SMS_CODE_CACHE + vo.getPhone());
            // 调用会员的远程服务
            return userService.insert(vo);
        }
        return R.failed("注册失败");
    }

    @PostMapping()
    public R login(UserLoginVo loginVo, HttpSession session){

        UserInfo login = userService.login(loginVo);
        if (login == null){
            return R.ok("登陆失败");
        }
        //第一次登录数据保存到session中
        session.setAttribute(AuthServerConstant.LOGIN_USER, login);
        return R.ok(login);
    }

}
