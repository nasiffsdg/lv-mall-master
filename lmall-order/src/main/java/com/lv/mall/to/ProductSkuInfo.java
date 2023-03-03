package com.lv.mall.to;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author 17324
 * @TableName lv_product_sku_info
 */
@TableName(value ="lv_product_sku_info")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductSkuInfo implements Serializable {
    /**
     * skuId
     */
    @TableId(value = "sku_id")
    private Long skuId;

    /**
     * spuId
     */
    @TableField(value = "spu_id")
    private Long spuId;

    /**
     * sku名称
     */
    @TableField(value = "sku_name")
    private String skuName;

    /**
     * sku介绍描述
     */
    @TableField(value = "sku_desc")
    private String skuDesc;

    /**
     * 所属分类id
     */
    @TableField(value = "catalog_id")
    private Long catalogId;

    /**
     * 品牌id
     */
    @TableField(value = "brand_id")
    private Long brandId;

    /**
     * 默认图片
     */
    @TableField(value = "image")
    private String image;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;
    /**
     * 价格
     */
    @TableField(value = "stock")
    private BigDecimal stock;
    /**
     * 销量
     */
    @TableField(value = "sale_count")
    private Long saleCount;

    /**
     * 
     */
    @TableField(value = "discount")
    private String discount;

    /**
     * 
     */
    @TableField(value = "full_price")
    private BigDecimal fullPrice;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}