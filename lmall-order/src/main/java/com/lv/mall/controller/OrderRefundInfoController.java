package com.lv.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.entity.OrderRefundInfo;
import com.lv.mall.service.OrderRefundInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (OrderRefundInfo)表控制层
 *
 * @author lv
 * @since 2023-03-03 14:06:48
 */
@RestController
@RequestMapping("orderRefundInfo")
public class OrderRefundInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private OrderRefundInfoService orderRefundInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param orderRefundInfo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<OrderRefundInfo> page, OrderRefundInfo orderRefundInfo) {
        return success(this.orderRefundInfoService.page(page, new QueryWrapper<>(orderRefundInfo)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.orderRefundInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param orderRefundInfo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody OrderRefundInfo orderRefundInfo) {
        return success(this.orderRefundInfoService.save(orderRefundInfo));
    }

    /**
     * 修改数据
     *
     * @param orderRefundInfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody OrderRefundInfo orderRefundInfo) {
        return success(this.orderRefundInfoService.updateById(orderRefundInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.orderRefundInfoService.removeByIds(idList));
    }
}

