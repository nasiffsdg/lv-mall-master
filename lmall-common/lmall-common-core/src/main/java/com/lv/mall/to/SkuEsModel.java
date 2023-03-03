package com.lv.mall.to;

import lombok.*;

import java.io.Serializable;
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
public class SkuEsModel implements Serializable{

    private long brandId;

    private String brandImage;

    private String brandName;

    private long catalogId;

    private String catalogName;

    private boolean hasStock;

    private long saleCount;

    private String skuDesc;

    private long skuId;

    private String skuImage;

    private String skuName;

    private BigDecimal skuPrice;

    private long spuId;

    private String spuName;

    private List<Attrs> attrs;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class Attrs implements Serializable {
        private long attrId;
        private String attrName;
        private String attrValue;
    }
}
