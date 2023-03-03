package com.lv.mall.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.lv.mall.constant.AuthServerConstant;
import com.lv.mall.constant.UserLevel;
import com.lv.mall.entity.UserInfo;
import com.lv.mall.interceptor.CartInterceptor;
import com.lv.mall.service.CartService;
import com.lv.mall.to.UserInfoTo;
import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 17324
 */
@RestController
@RequestMapping("/cart")
public class CartController {


    @Resource
    CartService cartService;

    @GetMapping("/list")
    public R cartList(HttpSession session){

        UserInfoTo userInfoTo = CartInterceptor.userInfoToThreadLocal.get();

        if (userInfoTo.getUserLevel() == UserLevel.NO_LOGIN){
            return R.ok("对不起你的用户没有登录").setCode(308);
        }
        return R.ok(JSON.toJSONString(cartService.getCart()));
    }

    @GetMapping("/add")
    public R addCart(@RequestParam Long skuId, @RequestParam Integer num){
        UserInfoTo userInfoTo = CartInterceptor.userInfoToThreadLocal.get();

        if (userInfoTo.getUserLevel() == UserLevel.NO_LOGIN){
            return R.ok("对不起你的用户没有登录").setCode(308);
        }
        return R.ok(cartService.addToCart(skuId, num));
    }

}
