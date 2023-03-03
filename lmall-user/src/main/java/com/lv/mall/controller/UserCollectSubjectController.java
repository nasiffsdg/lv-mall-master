package com.lv.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.entity.UserCollectSubject;
import com.lv.mall.service.UserCollectSubjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (UserCollectSubject)表控制层
 *
 * @author lv
 * @since 2023-03-01 22:55:03
 */
@RestController
@RequestMapping("user/userCollectSubject")
public class UserCollectSubjectController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserCollectSubjectService userCollectSubjectService;

    /**
     * 分页查询所有数据
     *
     * @param page               分页对象
     * @param userCollectSubject 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<UserCollectSubject> page, UserCollectSubject userCollectSubject) {
        return success(this.userCollectSubjectService.page(page, new QueryWrapper<>(userCollectSubject)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userCollectSubjectService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param userCollectSubject 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody UserCollectSubject userCollectSubject) {
        return success(this.userCollectSubjectService.save(userCollectSubject));
    }

    /**
     * 修改数据
     *
     * @param userCollectSubject 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody UserCollectSubject userCollectSubject) {
        return success(this.userCollectSubjectService.updateById(userCollectSubject));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userCollectSubjectService.removeByIds(idList));
    }
}

