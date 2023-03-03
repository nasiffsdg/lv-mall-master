package com.lv.mall.vo;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 17324
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SpuSaveVo {
    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private List<String> images;
    private List <Attr> baseAttrs;
    private List<SkuVo> skus;
}
