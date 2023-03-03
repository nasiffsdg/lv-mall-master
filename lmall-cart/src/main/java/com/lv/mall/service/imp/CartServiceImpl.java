package com.lv.mall.service.imp;

import com.alibaba.fastjson.JSON;
import com.lv.mall.entity.Cart;
import com.lv.mall.entity.CartItem;
import com.lv.mall.feign.ProductService;
import com.lv.mall.interceptor.CartInterceptor;
import com.lv.mall.service.CartService;
import com.lv.mall.to.UserInfoTo;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 17324
 */
@Service
public class CartServiceImpl implements CartService {

    private final String cartPyrex = "lmall:cart:";
    @Resource
    ProductService productService;

    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Override
    public CartItem addToCart(Long skuId, Integer num) {

        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        Object res = cartOps.get(skuId.toString());
        if (StringUtils.isEmpty(res)){
            HashMap<String, Object> skuData = (HashMap)productService.selectOne(skuId).getData();

            CartItem cartItem = new CartItem();
            cartItem.setCheck(true);
            cartItem.setCount(num);
            cartItem.setSkuId((Long) skuData.get("skuId"));
            cartItem.setPrice((Double) skuData.get("price"));
            cartItem.setTitle((String) skuData.get("skuDesc"));
            cartItem.setImage((String) skuData.get("image"));
            cartItem.setSkuAttrs(null);

            String item = JSON.toJSONString(cartItem);
            cartOps.put(cartItem.getSkuId().toString(), item);
            return cartItem;
        }else {
            // 修改数量
            CartItem cartItem = JSON.parseObject((String) res, CartItem.class);
            cartItem.setCount(cartItem.getCount()+num);
            String item = JSON.toJSONString(cartItem);
            cartOps.put(cartItem.getSkuId().toString(), item);
            return cartItem;
        }
    }

    @Override
    public Cart getCart() {

        BoundHashOperations<String, Object, Object> cartOps = getCartOps();

        Cart cart = new Cart();
        List<Object> values = cartOps.values();
        if (values!=null){
            List<CartItem> cartItems = values.stream().map(obj -> {
                String strObj = (String) obj;
                return JSON.parseObject(strObj, CartItem.class);
            }).collect(Collectors.toList());
            cart.setItems(cartItems);
        }
        return cart;
    }

    @Override
    public void clearCart(String key) {
        stringRedisTemplate.delete(key);
    }

    private BoundHashOperations<String, Object, Object> getCartOps(){
        UserInfoTo userInfoTo = CartInterceptor.userInfoToThreadLocal.get();
        Long userId = userInfoTo.getUserInfo().getId();
        String cartKey = cartPyrex + userId;
        return stringRedisTemplate.boundHashOps(cartKey);
    }
}
