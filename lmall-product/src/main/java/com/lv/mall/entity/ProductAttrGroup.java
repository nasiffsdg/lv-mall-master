package com.lv.mall.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.*;


/**
 * @author 17324
 * @TableName lv_product_band
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
/**
 * (ProductAttrGroup)表实体类
 *
 * @author lv
 * @since 2023-02-23 18:08:52
 */
@SuppressWarnings("serial")
@TableName("lv_product_attr_group")
public class ProductAttrGroup extends Model<ProductAttrGroup> {
    /**
     分组id
     */
    private Long attrGroupId;
    //组名
    private String attrGroupName;
    //排序
    private Integer sort;
    //描述
    private String descript;
    //组图标
    private String icon;
    //所属分类id
    private Long catelogId;
}

