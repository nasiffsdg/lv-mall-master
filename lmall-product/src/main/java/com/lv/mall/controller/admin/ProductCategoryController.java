package com.lv.mall.controller.admin;


import com.lv.mall.entity.ProductCategory;
import com.lv.mall.service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品二级分类(ProductCategory)表控制层
 *
 * @author lv
 * @since 2023-02-15 18:57:39
 */
@RestController
@RequestMapping("/product/productCategory")
public class ProductCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private ProductCategoryService productCategoryService;

    /**
     * 查询所有的分类
     *
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<List<ProductCategory>> queryAllCategory() throws InterruptedException {
        return ResponseEntity.ok(this.productCategoryService.queryByPageByLock());
    }

    /**
     * 根据id进行逻辑删除
     */
    @PostMapping("/deleteById")
    public ResponseEntity<Integer> queryAllCategory(@RequestBody Long[] catIds) {
        return ResponseEntity.ok(this.productCategoryService.removeMenuById(catIds));
    }

}

