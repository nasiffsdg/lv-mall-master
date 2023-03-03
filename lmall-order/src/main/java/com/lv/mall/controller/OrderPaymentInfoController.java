package com.lv.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.entity.OrderPaymentInfo;
import com.lv.mall.service.OrderPaymentInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (OrderPaymentInfo)表控制层
 *
 * @author lv
 * @since 2023-03-03 14:06:48
 */
@RestController
@RequestMapping("orderPaymentInfo")
public class OrderPaymentInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private OrderPaymentInfoService orderPaymentInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param orderPaymentInfo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<OrderPaymentInfo> page, OrderPaymentInfo orderPaymentInfo) {
        return success(this.orderPaymentInfoService.page(page, new QueryWrapper<>(orderPaymentInfo)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.orderPaymentInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param orderPaymentInfo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody OrderPaymentInfo orderPaymentInfo) {
        return success(this.orderPaymentInfoService.save(orderPaymentInfo));
    }

    /**
     * 修改数据
     *
     * @param orderPaymentInfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody OrderPaymentInfo orderPaymentInfo) {
        return success(this.orderPaymentInfoService.updateById(orderPaymentInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.orderPaymentInfoService.removeByIds(idList));
    }
}

