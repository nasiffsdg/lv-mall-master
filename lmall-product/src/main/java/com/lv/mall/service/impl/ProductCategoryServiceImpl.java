package com.lv.mall.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lv.mall.entity.ProductCategory;
import com.lv.mall.mapper.ProductCategoryMapper;
import com.lv.mall.service.ProductCategoryService;
import com.mysql.cj.util.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.cache.CacheManager;
import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 17324
 * @description 针对表【lv_product_category(商品二级分类)】的数据库操作Service实现
 * @createDate 2023-02-15 18:55:24
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory>
        implements ProductCategoryService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    ProductCategoryMapper productCategoryMapper;

    @Resource
    RedissonClient client;

    @Override
    public List<ProductCategory> queryByPage() {

        //TODO: 空结果缓存
        //TODO: 设置随机的过期时间
        //TODO: 加锁 查询数据库就要加锁

        List<ProductCategory> categories;
        // 加入缓存逻辑
        String catJSON = stringRedisTemplate.opsForValue().get("catJSON");
        if (StringUtils.isNullOrEmpty(catJSON)) {
            // 缓存中没有数据 放入缓存
            synchronized (this) {
                categories = queryByPageFromDb();
                String jsonString = JSON.toJSONString(categories);
                stringRedisTemplate.opsForValue().set("catJSON", jsonString, 2000 + new Random().nextInt(50), TimeUnit.SECONDS);
            }
        } else {
            // 反序列化
            categories = JSON.parseArray(catJSON, ProductCategory.class);
        }
        return categories;
    }

    @Override
    public List<ProductCategory> queryByPageByLock() throws InterruptedException {

        //TODO: 空结果缓存
        //TODO: 设置随机的过期时间
        //TODO: 加锁 查询数据库就要加锁

        List<ProductCategory> categories = null;
        // 加入缓存逻辑
        String catJSON = stringRedisTemplate.opsForValue().get("catJSON");
        if (StringUtils.isNullOrEmpty(catJSON)) {

            String uuid = UUID.randomUUID().toString();
            // 加锁
            while (!getLock(uuid)) {
                // 重试
                Thread.sleep(200);
            }

            try {

                categories = queryByPageFromDb();
                String jsonString = JSON.toJSONString(categories);
                stringRedisTemplate.opsForValue().set("catJSON", jsonString, 2000 + new Random().nextInt(50), TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                releaseLock(uuid);
            }

        } else {
            // 反序列化
            categories = JSON.parseArray(catJSON, ProductCategory.class);
        }
        return categories;
    }

    @Override
    public List<ProductCategory> queryByPageByRedissonLock() throws InterruptedException {


        List<ProductCategory> categories = null;
        // 名字相同就是同一个锁
        // 这就牵涉到锁的粒度文体
        RLock lock = client.getLock("cate_lock");
        // 加入缓存逻辑
        String catJSON = stringRedisTemplate.opsForValue().get("catJSON");
        if (StringUtils.isNullOrEmpty(catJSON)) {

            String uuid = UUID.randomUUID().toString();
            // 加锁
            lock.lock();
            try {

                categories = queryByPageFromDb();
                String jsonString = JSON.toJSONString(categories);
                stringRedisTemplate.opsForValue().set("catJSON", jsonString, 2000 + new Random().nextInt(50), TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        } else {
            // 反序列化
            categories = JSON.parseArray(catJSON, ProductCategory.class);
        }
        return categories;
    }

    public List<ProductCategory> queryByPageFromDb() {


        List<ProductCategory> categories;
        String catalogJSON = stringRedisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isNullOrEmpty(catalogJSON)) {
            // 从数据库查询所有的分类

            List<ProductCategory> categoryList = productCategoryMapper.selectList(null);

            // 进行组装
            // 找到所有的一级分类
            return categoryList.stream().
                    filter(productCategory -> productCategory.getParentId() == 0).
                    peek(manu -> {
                        // 设置其子菜单
                        manu.setChildren(getChildren(manu, categoryList));
                    }).collect(Collectors.toList());
        } else {
            categories = JSON.parseArray(catalogJSON, ProductCategory.class);
        }
        return categories;


    }


    @Override
    public int removeMenuById(Long[] catIds) {

        //TODO 检查当前删除的菜单是否被别人引用

        // 逻辑删除不直接删除 只是把一位设置false就直接不显示
        return productCategoryMapper.deleteBatchIds(List.of(catIds));
    }


    private List<ProductCategory> getChildren(ProductCategory category, List<ProductCategory> categoryList) {
        return categoryList.stream().filter(category1 -> category1.getParentId().equals(category.getCatId())).collect(Collectors.toList());
    }

    private boolean getLock(String token) {
        boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("LOCK", token, 2000, TimeUnit.SECONDS);
        return true;
    }

    private void releaseLock(String token) {
        String lock = stringRedisTemplate.opsForValue().get("LOCK");
        // lua脚本解锁
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
                "then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";
        stringRedisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList("LOCK"),lock);
    }

}




