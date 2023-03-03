package com.lv.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lv.mall.mapper.ProductBandMapper;
import com.lv.mall.entity.ProductBand;
import com.lv.mall.mapper.ProductCategoryMapper;
import com.lv.mall.service.ProductBandService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 17324
* @description 针对表【lv_product_category(商品二级分类)】的数据库操作Service实现
* @createDate 2023-02-15 18:55:24
*/
@Service
public class ProductBandServiceImpl extends ServiceImpl<ProductBandMapper, ProductBand>
    implements ProductBandService {
    @Resource
    ProductCategoryMapper categoryMapper;

    @Cacheable(value = "product-brand", key = "'all_list'")
    @Override
    public List<ProductBand> listAll() {
        return this.list();
    }
}




