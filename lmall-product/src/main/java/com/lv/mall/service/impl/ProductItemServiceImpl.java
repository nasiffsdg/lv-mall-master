package com.lv.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.lv.mall.entity.ProductAttr;
import com.lv.mall.entity.ProductAttrGroup;
import com.lv.mall.entity.ProductSpuAttrValue;
import com.lv.mall.entity.ProductSpuInfo;
import com.lv.mall.service.*;
import com.lv.mall.to.SkuEsModel;
import com.lv.mall.vo.SpuItemVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 17324
 */
@Service
public class ProductItemServiceImpl implements ProductItemService {

    @Resource
    ProductSkuInfoService skuInfoService;

    @Resource
    ProductSpuInfoService spuInfoService;

    @Resource
    ProductAttrService attrService;

    @Resource
    ProductAttrGroupService attrGroupService;

    @Resource
    ProductSpuAttrValueService spuAttrValueService;

    @Resource
    ProductSkuAttrValueService skuAttrValueService;

    @Resource
    ThreadPoolExecutor poolExecutor;

    @Override
    public SpuItemVo spuItem(Long spuId) throws ExecutionException, InterruptedException {

        SpuItemVo spuItemVo = new SpuItemVo();
        CompletableFuture<ProductSpuInfo> infoCompletableFuture = CompletableFuture.supplyAsync(() -> {
            //spu基本信息
            ProductSpuInfo spuInfo = spuInfoService.getById(spuId);
            spuItemVo.setSpuInfo(spuInfo);
            return spuInfo;
        }, poolExecutor);
        infoCompletableFuture.thenAcceptAsync((res)->{
            //sku基本信息
            spuItemVo.setSkus(skuInfoService.getSkusById(spuId));
        });

        //spu基本属性

//        List<ProductAttrGroup> attrGroups = attrGroupService.list(new QueryWrapper<ProductAttrGroup>().eq("catelog_id", spuInfo.getCatalogId()));
//
//        SpuItemVo.SpuItemBaseAttrVo spuItemBaseAttrVo = new SpuItemVo.SpuItemBaseAttrVo();
//
//        // 属性分组
//        for (ProductAttrGroup attrGroup : attrGroups) {
//            spuItemBaseAttrVo.setGroupName(attrGroup.getAttrGroupName());
//            ArrayList<SkuEsModel.Attrs> attrs = new ArrayList<>();
//            // 根据属性分组查属性
//            List<ProductAttr> attrList = attrService.list(new QueryWrapper<ProductAttr>().eq("group_id", attrGroup.getAttrGroupId()));
//            for (ProductAttr attr : attrList) {
//            }
//
//        }

        // spu基本属性的组装
        // sku 属性的组装

        // 阻塞等结果
        CompletableFuture.allOf(infoCompletableFuture).get();

        return spuItemVo;
    }
}
