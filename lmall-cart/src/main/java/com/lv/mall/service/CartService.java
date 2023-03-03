package com.lv.mall.service;

import com.lv.mall.entity.Cart;
import com.lv.mall.entity.CartItem;

/**
 * @author 17324
 */
public interface CartService {


    /**
     * 商品添加到购物车
     *
     * @param skuId
     * @param num
     * @return
     */
    CartItem addToCart(Long skuId, Integer num);

    /**
     * 获取购物车
     * @return
     */
    Cart getCart();

    void clearCart(String key);
}
