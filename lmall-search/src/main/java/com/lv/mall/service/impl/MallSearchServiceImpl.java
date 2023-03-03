package com.lv.mall.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lv.mall.constant.EsConstant;
import com.lv.mall.service.MallSearchService;
import com.lv.mall.to.SkuEsModel;
import com.lv.mall.vo.SearchParam;
import com.lv.mall.vo.SearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 17324
 */
@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Resource
    RestHighLevelClient client;

    @Override
    @Cacheable("search-cache")
    public SearchResponse search(SearchParam param) throws IOException {

        // 构建检索请求
        SearchRequest searchRequest = buildrRequest(param);

        org.elasticsearch.action.search.SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        return buildSearchResponse(response, param);
    }


    private SearchRequest buildrRequest(SearchParam param){

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        /**
         * 模糊匹配过滤
         */
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        // 模糊匹配
        if (!StringUtils.isEmpty(param.getKeyWord())){
            queryBuilder.must(QueryBuilders.matchQuery("skuDesc", param.getKeyWord()));
        }
        // 过滤查寻
        if (!StringUtils.isEmpty(param.getCategoryId())) {
            queryBuilder.filter(QueryBuilders.termQuery("catalogId", param.getCategoryId()));
        }
        if (!StringUtils.isEmpty(param.getBrandId())){
            queryBuilder.filter(QueryBuilders.termQuery("brandId", param.getBrandId()));
        }
        // 库存
        if (!StringUtils.isEmpty(param.getHasStock())){
            queryBuilder.filter(QueryBuilders.termQuery("hasStock", (param.getHasStock()) == 1));
        }
        // 价格区间
        if (!StringUtils.isEmpty(param.getPriceLow()) && !StringUtils.isEmpty(param.getPriceHigh())){
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("skuPrice");
            if (StringUtils.isEmpty(param.getPriceLow())){
                rangeQuery.gte(param.getPriceLow());
            }
            if (StringUtils.isEmpty(param.getPriceHigh())){
                rangeQuery.lte(param.getPriceHigh());
            }
            queryBuilder.filter(rangeQuery);
        }

        /**
         * 排序 分页
         * 字段_升降序
         */
        if (StringUtils.isEmpty(param.getSort())){
            //TODO 排序
        }

        /**
         * 分页
         * from = (pageNum - 1) * pageSize
         */
        sourceBuilder.from((param.getPageNum()-1)*EsConstant.PAGE_SIZE);
        sourceBuilder.size(EsConstant.PAGE_SIZE);
        sourceBuilder.query(queryBuilder);


        /**
         * 聚合分析
         */
        TermsAggregationBuilder brandAgg = AggregationBuilders.terms("brandAgg");
        brandAgg.field("brandId").size(50);


        TermsAggregationBuilder catAgg = AggregationBuilders.terms("catAgg");
        catAgg.field("catalogId").size(50);


        sourceBuilder.aggregation(brandAgg);
        sourceBuilder.aggregation(catAgg);


        SearchRequest request = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, sourceBuilder);
        return request;
    }

    private SearchResponse buildSearchResponse(org.elasticsearch.action.search.SearchResponse response, SearchParam param){

        SearchResponse result = new SearchResponse();


        //1、返回的所有查询到的商品
        SearchHits hits = response.getHits();

        List<SkuEsModel> esModels = new ArrayList<>();
        //遍历所有商品信息
        if (hits.getHits() != null && hits.getHits().length > 0) {
            for (SearchHit hit : hits.getHits()) {
                String sourceAsString = hit.getSourceAsString();
                SkuEsModel esModel = JSON.parseObject(sourceAsString, SkuEsModel.class);

                esModels.add(esModel);
            }
        }
        result.setProducts(esModels);



        //3、当前商品涉及到的所有品牌信息
        List<Long> brandVos = new ArrayList<>();
        //获取到品牌的聚合
        ParsedLongTerms brandAgg = response.getAggregations().get("brandAgg");
        for (Terms.Bucket bucket : brandAgg.getBuckets()) {
            brandVos.add((Long) bucket.getKey());
        }
        result.setBands(brandVos);

        //4、当前商品涉及到的所有分类信息
        //获取到分类的聚合
        List<Long> catalogVos = new ArrayList<>();
        ParsedLongTerms catalogAgg = response.getAggregations().get("catAgg");
        for (Terms.Bucket bucket : catalogAgg.getBuckets()) {
            catalogVos.add((Long) bucket.getKey());
        }
        result.setCategories(catalogVos);

        //===============以上可以从聚合信息中获取====================//
        //5、分页信息-页码
        result.setPageNum(param.getPageNum());
        //5、1分页信息、总记录数
        long total = hits.getTotalHits().value;
        result.setTotal((int) total);

        //5、2分页信息-总页码-计算
        int totalPages = (int)total % EsConstant.PAGE_SIZE == 0 ?
                (int)total / EsConstant.PAGE_SIZE : ((int)total / EsConstant.PAGE_SIZE + 1);
        result.setTotalPages(totalPages);

        return result;
    }
}
