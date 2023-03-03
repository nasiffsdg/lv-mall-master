package com.lv.mall.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author 17324
 * @TableName lv_product_band
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@TableName(value ="lv_product_band")
public class ProductBand implements Serializable {
    /**
     * 
     */
    @TableId(value = "brand_id")
    private Long brandId;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * logo地址
     */
    @TableField(value = "logo")
    private String logo;

    /**
     * 
     */
    @TableField(value = "descript")
    private String descript;

    /**
     * 
     */
    @TableField(value = "show_status")
    private Integer showStatus;

    /**
     * 帮助进行快速的检索
     */
    @TableField(value = "first_letter")
    private String firstLetter;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}