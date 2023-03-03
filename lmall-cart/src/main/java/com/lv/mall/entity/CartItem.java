package com.lv.mall.entity;

import lombok.Data;
import org.redisson.api.LockOptions;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 17324
 */
public class CartItem {


      private Long skuId;
      private Boolean check;
      private String title;
      private String image;
      private Double  price;
      private Integer count;
      private List<String> skuAttrs;
      private Double TotalPrice;

    public Double getTotalPrice() {
        return price*count;
    }

    public void setTotalPrice(Double totalPrice) {
        TotalPrice = totalPrice;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<String> getSkuAttrs() {
        return skuAttrs;
    }

    public void setSkuAttrs(List<String> skuAttrs) {
        this.skuAttrs = skuAttrs;
    }

}
