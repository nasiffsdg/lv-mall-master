package com.lv.mall.vo;

import com.lv.mall.entity.ProductSkuInfo;
import com.lv.mall.entity.ProductSpuInfo;
import com.lv.mall.to.SkuEsModel;
import lombok.*;

import javax.print.DocFlavor;
import java.util.List;

/**
 * @author 17324
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SpuItemVo {

    /**
     *   spu信息
      */
    private List<ProductSkuInfo> skus;

    /**
     * sku信息
     */
    private ProductSpuInfo spuInfo;

    /**
     * spu的基本属性
     */
    private List<SkuItemSaleAttrVo> baseSpuAttrs;

    /**
     * sku规格参数
     */
    private List<SkuItemSaleAttrVo> skuAttrs;

    /**
     * sku 销售属性
     */
    private List<SkuItemSaleAttrVo> skuItemSaleAttrVos;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SkuItemSaleAttrVo{

        private Long attrId;
        private String attrName;
        private List<String> attrValues;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpuItemBaseAttrVo{

        private String groupName;
        private List<SkuEsModel.Attrs> attrs;
    }


}
