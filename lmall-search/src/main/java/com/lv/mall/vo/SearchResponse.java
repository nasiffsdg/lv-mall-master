package com.lv.mall.vo;

import com.lv.mall.to.ProductBand;
import com.lv.mall.to.ProductCategory;
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
public class SearchResponse {

    /**
     查询到的所有商品信息
      */
    private List<SkuEsModel> products;

    /**
     * 聚合数据
     */
    private List<Long> categories;
    private List<Long> bands;
    /**
     * 分页
     */
    private int pageNum;
    private int total;
    private int totalPages;
}
