package com.lv.mall.to;



import lombok.*;

import java.io.Serializable;

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
public class ProductBand implements Serializable {
    /**
     * 
     */
    private Long brandId;

    /**
     * 
     */
    private String name;

    /**
     * logo地址
     */
    private String logo;

    /**
     * 
     */
    private String descript;

    /**
     * 
     */
    private Integer showStatus;

    /**
     * 帮助进行快速的检索
     */
    private String firstLetter;


}