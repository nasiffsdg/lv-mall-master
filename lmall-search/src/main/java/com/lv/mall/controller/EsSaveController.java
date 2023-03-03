package com.lv.mall.controller;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.api.R;
import com.lv.mall.service.ProductSaveService;
import com.lv.mall.to.SkuEsModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 17324
 */
@Slf4j
@RestController
@RequestMapping("/search/save")
public class EsSaveController {

    @Resource
    ProductSaveService productSaveService;

    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels){

        System.out.println(skuEsModels);
        try {
            return R.ok(productSaveService.productStatusUp(skuEsModels));
        }catch (Exception e){
            log.error("商品上架错误");
            e.printStackTrace();
        }
        return R.failed("501");
    }
}
