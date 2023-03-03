package com.lv.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lv.mall.entity.ProductAttrGroup;

/**
 * (ProductAttrGroup)表服务接口
 *
 * @author lv
 * @since 2023-02-23 18:08:53
 */
public interface ProductAttrGroupService extends IService<ProductAttrGroup> {


    /**
     * 根据 当前的三级分类的id 分页查询所有的数据
     * @param page
     * @param id
     * @return
     */
    Page<ProductAttrGroup> getPageById(Page<ProductAttrGroup> page, Long id, String key);

}

