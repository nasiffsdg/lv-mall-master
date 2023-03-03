package com.lv.mall.service;

import com.lv.mall.vo.SpuItemVo;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ExecutionException;

/**
 * @author 17324
 */
public interface ProductItemService {

    /**
     * 根据商品Id查询商品的详情信息
     *
     * @param skuId
     * @return
     */
    public SpuItemVo spuItem (Long skuId) throws ExecutionException, InterruptedException;
}
