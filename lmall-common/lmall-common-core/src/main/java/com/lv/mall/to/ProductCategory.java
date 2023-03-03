package com.lv.mall.to;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 商品二级分类
 * @author 17324
 * @TableName lv_product_category
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCategory implements Serializable {
    /**
     * 分类id
     */
    private Long catId;

    /**
     * 分类名称
     */
    private String catName;

    /**
     * 父分类id
     */
    private Long parentId;

    /**
     * 层级
     */
    private Integer catLevel;

    /**
     * 计量单位
     */
    private String productUnit;

    /**
     * 商品数量
     */
    private Integer productCount;


    private List<ProductCategory> children;

    private static final long serialVersionUID = 1L;

    private Integer showStatus;
}