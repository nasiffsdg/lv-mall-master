package com.lv.mall.controller;

import com.lv.mall.service.MallSearchService;
import com.lv.mall.vo.SearchParam;
import com.lv.mall.vo.SearchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author 17324
 */
@RestController
@RequestMapping("/search")
public class SearchService {


    @Resource
    MallSearchService mallSearchService;

    @PostMapping("/product")
    public ResponseEntity<SearchResponse> search(@RequestBody SearchParam searchParam) throws IOException {


        return ResponseEntity.ok(mallSearchService.search(searchParam));
    }

}
