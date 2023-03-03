package com.lv.mall.service;

import com.lv.mall.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 17324
* @description 针对表【lv_product_category(商品二级分类)】的数据库操作Service
* @createDate 2023-02-15 18:55:24
*/
public interface ProductCategoryService extends IService<ProductCategory> {

    /**
     * 查询所有的分类
     * @return
     */
    List<ProductCategory> queryByPage();

    /**
     * 根据 id进行逻辑删除
     * @param catIds
     * @return
     */
    int removeMenuById(Long[] catIds);

    /**
     * redis 分布式锁
     * @return
     * @throws InterruptedException
     */
    public List<ProductCategory> queryByPageByLock() throws InterruptedException;

    /**
     * redisson 实现分布式锁并
     * @return
     * @throws InterruptedException
     */
    public List<ProductCategory> queryByPageByRedissonLock() throws InterruptedException;
}
