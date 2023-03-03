package com.lv.mall.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 17324
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SkuVo {
    private String skuName;
    private String skuDesc;
    private List<Attr> attrs;
    private BigDecimal price;
    private BigDecimal fullPrice;
    private BigDecimal discount;
    private int priceStatue;
    private List<String> images;
    private int stock;
}
