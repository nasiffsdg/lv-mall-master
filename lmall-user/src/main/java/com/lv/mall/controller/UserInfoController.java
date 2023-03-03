package com.lv.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.UserApplication;
import com.lv.mall.entity.UserInfo;
import com.lv.mall.service.UserInfoService;
import com.lv.mall.vo.UserLoginVo;
import com.lv.mall.vo.UserRegisterVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (UserInfo)表控制层
 *
 * @author lv
 * @since 2023-03-01 22:55:04
 */
@RestController
@RequestMapping("user/userInfo")
public class UserInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserInfoService userInfoService;


    /**
     * 用户登录
     * @param loginVo
     * @return
     */
    @PostMapping("/login")
    public UserInfo login(@RequestBody UserLoginVo loginVo){
        return userInfoService.login(loginVo);
    }
    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param userInfo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<UserInfo> page, UserInfo userInfo) {
        return success(this.userInfoService.page(page, new QueryWrapper<>(userInfo)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param  registerVo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody UserRegisterVo registerVo) {
        try {
            this.userInfoService.registerByVo(registerVo);
        }catch (Exception e){
            return R.failed(e.getMessage());
        }
        return R.ok("保存成功");
    }

    /**
     * 修改数据
     *
     * @param userInfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody UserInfo userInfo) {
        return success(this.userInfoService.updateById(userInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userInfoService.removeByIds(idList));
    }
}

