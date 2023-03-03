package com.lv.mall.vo;

import com.lv.mall.to.SkuEsModel;
import lombok.*;

import java.util.List;

/**
 * @author 17324
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SearchParam {

    /**
     * 根据skuDec进行全文检索 一个包含多个数据的全文
     */
    private String keyWord;
    private String categoryId;
    private String brandId;

    /**
     * 排序条件 销量 价格
     * price_desc
     * price_asc
     * sell_desc
     * sell_asc
     */
    private String sort;
    /**
     * 筛选体条件
     * 有货
     * 价格区间
     * 属性值
     */
    private Integer hasStock;
    private Integer priceLow;
    private Integer priceHigh;

    /**
     * 分页
     */
    private int pageNum = 1;
    private int total;
    private int totalPages;
}
