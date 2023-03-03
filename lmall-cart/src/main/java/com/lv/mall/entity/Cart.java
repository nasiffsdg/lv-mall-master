package com.lv.mall.entity;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 17324
 */
public class Cart {

    /**
     * 购物车子项信息
     */
    private List<CartItem> items;

    /**
     * 商品数量
     */
    private Integer countNum;

    /**
     * 商品类型数量
     */
    private Integer countType;

    /**
     * 商品总价
     */
    private Double totalAmount;



    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public void setCountType(Integer countType) {
        this.countType = countType;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getCountNum() {
        int count = 0;
        if (items != null && items.size() > 0) {
            for (CartItem item : items) {
                count += item.getCount();
            }
        }
        return count;
    }

    public Integer getCountType() {
        int count = 0;
        if (items != null && items.size() > 0) {
            for (CartItem item : items) {
                count += 1;
            }
        }
        return count;
    }


    public Double getTotalAmount() {
        Double amount = new Double(0);
        // 计算购物项总价
        if (!CollectionUtils.isEmpty(items)) {
            for (CartItem cartItem : items) {
                if (cartItem.getCheck()) {
                    amount = amount + cartItem.getTotalPrice();
                }
            }
        }
        // 计算优惠后的价格
        return amount;
    }
}
