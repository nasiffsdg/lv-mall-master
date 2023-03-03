package com.lv.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.entity.UserReceiveAddress;
import com.lv.mall.service.UserReceiveAddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (UserReceiveAddress)表控制层
 *
 * @author lv
 * @since 2023-03-01 22:55:05
 */
@RestController
@RequestMapping("user/userReceiveAddress")
public class UserReceiveAddressController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserReceiveAddressService userReceiveAddressService;

    /**
     * 分页查询所有数据
     *
     * @param page               分页对象
     * @param userReceiveAddress 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<UserReceiveAddress> page, UserReceiveAddress userReceiveAddress) {
        return success(this.userReceiveAddressService.page(page, new QueryWrapper<>(userReceiveAddress)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userReceiveAddressService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param userReceiveAddress 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody UserReceiveAddress userReceiveAddress) {
        return success(this.userReceiveAddressService.save(userReceiveAddress));
    }

    /**
     * 修改数据
     *
     * @param userReceiveAddress 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody UserReceiveAddress userReceiveAddress) {
        return success(this.userReceiveAddressService.updateById(userReceiveAddress));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userReceiveAddressService.removeByIds(idList));
    }
}

