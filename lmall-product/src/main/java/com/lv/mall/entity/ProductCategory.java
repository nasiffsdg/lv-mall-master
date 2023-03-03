package com.lv.mall.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.List;

import lombok.*;

/**
 * 商品二级分类
 * @author 17324
 * @TableName lv_product_category
 */
@TableName(value ="lv_product_category")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCategory implements Serializable {
    /**
     * 分类id
     */
    @TableId(value = "cat_id", type = IdType.AUTO)
    private Long catId;

    /**
     * 分类名称
     */
    @TableField(value = "cat_name")
    private String catName;

    /**
     * 父分类id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 层级
     */
    @TableField(value = "cat_level")
    private Integer catLevel;

    /**
     * 计量单位
     */
    @TableField(value = "product_unit")
    private String productUnit;

    /**
     * 商品数量
     */
    @TableField(value = "product_count")
    private Integer productCount;


    @TableField(value = "false")
    private List<ProductCategory> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(value = "show_status")
    @TableLogic(value = "1", delval = "0")
    private Integer showStatus;
}