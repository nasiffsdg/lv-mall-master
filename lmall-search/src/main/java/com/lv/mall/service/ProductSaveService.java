package com.lv.mall.service;

import com.lv.mall.to.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @author 17324
 */
public interface ProductSaveService {
    /**
     * @param skuEsModels
     * @return
     */
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
