package com.lv.mall.service;

import com.lv.mall.vo.SearchParam;
import com.lv.mall.vo.SearchResponse;

import java.io.IOException;

/**
 * @author 17324
 */
public interface MallSearchService {



    public SearchResponse search(SearchParam param) throws IOException;
}
