package com.lv.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.entity.UserCollectSpu;
import com.lv.mall.service.UserCollectSpuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (UserCollectSpu)表控制层
 *
 * @author lv
 * @since 2023-03-01 22:55:04
 */
@RestController
@RequestMapping("user/userCollectSpu")
public class UserCollectSpuController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserCollectSpuService userCollectSpuService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param userCollectSpu 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<UserCollectSpu> page, UserCollectSpu userCollectSpu) {
        return success(this.userCollectSpuService.page(page, new QueryWrapper<>(userCollectSpu)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userCollectSpuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param userCollectSpu 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody UserCollectSpu userCollectSpu) {
        return success(this.userCollectSpuService.save(userCollectSpu));
    }

    /**
     * 修改数据
     *
     * @param userCollectSpu 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody UserCollectSpu userCollectSpu) {
        return success(this.userCollectSpuService.updateById(userCollectSpu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userCollectSpuService.removeByIds(idList));
    }
}

