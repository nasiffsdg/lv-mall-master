package com.lv.mall.controller.app;

import com.lv.mall.service.*;
import com.lv.mall.vo.SpuItemVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author 17324
 */
@RestController("/productItem")
public class ItemController {


    @Resource
    ProductItemService productItemService;

    @GetMapping("{spuId}")
    public ResponseEntity<SpuItemVo> spuItem(@PathVariable Long spuId) throws ExecutionException, InterruptedException {

        return ResponseEntity.ok(productItemService.spuItem(spuId));
    }
}
