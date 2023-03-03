package com.lv.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lv.mall.entity.ProductBand;

import java.util.List;

/**
* @author 17324
* @description 针对表【lv_product_band】的数据库操作Service
* @createDate 2023-02-22 23:28:54
*/
public interface ProductBandService extends IService<ProductBand> {

    /**
     * 分页查询所有的品牌
     * @return
     */
    List<ProductBand> listAll();
}
