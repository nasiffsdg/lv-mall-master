package com.lv.mall.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.entity.ProductAttr;
import com.lv.mall.service.ProductAttrService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (ProductAttr)表控制层
 *
 * @author lv
 * @since 2023-02-24 14:25:49
 */
@RestController
@RequestMapping("product/productAttr")
public class ProductAttrController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProductAttrService productAttrService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param productAttr 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<ProductAttr> page, ProductAttr productAttr) {
        return success(this.productAttrService.page(page, new QueryWrapper<>(productAttr)));
    }

    /**
     * 通过属性分组主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(Page<ProductAttr> page, @PathVariable Serializable id) {
        return success(this.productAttrService.page(page, new QueryWrapper<ProductAttr>().eq("group_id", id)));
    }

    /**
     * 新增数据
     *
     * @param productAttr 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody ProductAttr productAttr) {
        return success(this.productAttrService.save(productAttr));
    }

    /**
     * 修改数据
     *
     * @param productAttr 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody ProductAttr productAttr) {
        return success(this.productAttrService.updateById(productAttr));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.productAttrService.removeByIds(idList));
    }
}

