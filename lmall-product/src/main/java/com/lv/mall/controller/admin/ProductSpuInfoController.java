package com.lv.mall.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.entity.ProductSpuInfo;
import com.lv.mall.service.ProductSpuInfoService;
import com.lv.mall.vo.SpuSaveVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * (ProductSpuInfo)表控制层
 *
 * @author lv
 * @since 2023-02-24 21:47:11
 */
@RestController
@RequestMapping("product/productSpuInfo")
public class ProductSpuInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProductSpuInfoService productSpuInfoService;


    /**
     * 商品的新建
     * @param spuSaveVo
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody SpuSaveVo spuSaveVo){
        productSpuInfoService.saveAll(spuSaveVo);
        System.out.println(spuSaveVo);
        return success("");
    }

    /**
     * 按条件分页查询所有数据
     *
     * @param page           分页对象
     * @param params 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<ProductSpuInfo> page, Map<String, Object> params) {
        return success(this.productSpuInfoService.queryPageByCondition(page, params));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.productSpuInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param productSpuInfo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody ProductSpuInfo productSpuInfo) {
        return success(this.productSpuInfoService.save(productSpuInfo));
    }

    /**
     * 修改数据
     *
     * @param productSpuInfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody ProductSpuInfo productSpuInfo) {
        return success(this.productSpuInfoService.updateById(productSpuInfo));
    }

    /**
     * 商品上架
     *
     * @param spuId 实体对象
     * @return 新增结果
     */
    @GetMapping("/{spuId}/up")
    public R insert(@PathVariable long spuId) {

        return success(this.productSpuInfoService.up(spuId));

    }
    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.productSpuInfoService.removeByIds(idList));
    }
}

