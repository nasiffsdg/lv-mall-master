package com.lv.mall.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.entity.ProductSkuInfo;
import com.lv.mall.entity.ProductSpuInfo;
import com.lv.mall.service.ProductSkuInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * (ProductSkuInfo)表控制层
 *
 * @author lv
 * @since 2023-02-24 23:33:31
 */
@RestController
@RequestMapping("product/productSkuInfo")
public class ProductSkuInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProductSkuInfoService productSkuInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param params 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<ProductSkuInfo> page, Map<String, Object> params) {
        return success(this.productSkuInfoService.queryPageByCondition(page, params));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.productSkuInfoService.getById(id));
    }



    /**
     * 修改数据
     *
     * @param productSkuInfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody ProductSkuInfo productSkuInfo) {
        return success(this.productSkuInfoService.updateById(productSkuInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.productSkuInfoService.removeByIds(idList));
    }
}

