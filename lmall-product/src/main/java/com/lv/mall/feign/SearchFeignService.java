package com.lv.mall.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.lv.mall.to.SkuEsModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author 17324
 */
@FeignClient("search-server")
public interface SearchFeignService {

    /**
     *
     * @param skuEsModels
     * @return
     */
    @PostMapping("/search/save/product")
    public R productStatusUp(List<SkuEsModel> skuEsModels);
}
