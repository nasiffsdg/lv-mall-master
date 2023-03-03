package com.lv.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.entity.StockInfo;
import com.lv.mall.service.StockInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (StockInfo)表控制层
 *
 * @author lv
 * @since 2023-02-25 00:10:17
 */
@RestController
@RequestMapping("stockInfo")
public class StockInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private StockInfoService stockInfoService;

    /**
     * 根据skuid分页查询所有数据
     *
     * @param page      分页对象
     * @param stockInfo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<StockInfo> page, StockInfo stockInfo) {
        return success(this.stockInfoService.page(page, new QueryWrapper<>(stockInfo)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.stockInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param stockInfo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody StockInfo stockInfo) {
        return success(this.stockInfoService.save(stockInfo));
    }

    /**
     * 修改数据
     *
     * @param stockInfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody StockInfo stockInfo) {
        return success(this.stockInfoService.updateById(stockInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.stockInfoService.removeByIds(idList));
    }
}

