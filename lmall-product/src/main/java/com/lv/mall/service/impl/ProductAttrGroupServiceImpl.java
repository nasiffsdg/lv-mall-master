package com.lv.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lv.mall.mapper.ProductAttrGroupMapper;
import com.lv.mall.entity.ProductAttrGroup;
import com.lv.mall.service.ProductAttrGroupService;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (ProductAttrGroup)表服务实现类
 *
 * @author lv
 * @since 2023-02-23 18:08:53
 */
@Service("productAttrGroupService")
public class ProductAttrGroupServiceImpl extends ServiceImpl<ProductAttrGroupMapper, ProductAttrGroup> implements ProductAttrGroupService {

    @Resource
    private ProductAttrGroupMapper productAttrGroupMapper;


    @Override
    public Page<ProductAttrGroup> getPageById(Page<ProductAttrGroup> page, Long id, String key) {

        if (id == 0){
            Page<ProductAttrGroup> pages = this.page(page, new QueryWrapper<>());
            return pages;
        }
        QueryWrapper<ProductAttrGroup> wrapper = new QueryWrapper<ProductAttrGroup>().eq("catelog_id", id);
        if (!StringUtils.isNullOrEmpty(key)){
            wrapper.and((obj)->{
                obj.eq("attr_group_name", key);
            });
        }
        return this.page(page, wrapper);
    }

}

