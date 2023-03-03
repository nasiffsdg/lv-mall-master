package com.lv.mall.feign;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

/**
 * @author 17324
 */
@FeignClient("product-server")
public interface ProductService {


    @GetMapping("product/productSkuInfo/{id}")
    public R selectOne(@PathVariable Serializable id);

}
