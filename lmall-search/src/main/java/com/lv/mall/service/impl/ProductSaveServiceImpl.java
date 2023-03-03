package com.lv.mall.service.impl;

import com.alibaba.fastjson2.JSON;
import com.lv.mall.constant.EsConstant;
import com.lv.mall.service.ProductSaveService;
import com.lv.mall.to.SkuEsModel;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author 17324
 */
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

    @Resource
    RestHighLevelClient client;

    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {


        BulkRequest bulkRequest = new BulkRequest();

        for (SkuEsModel esModel : skuEsModels) {
            IndexRequest request = new IndexRequest();

            request.index(EsConstant.PRODUCT_INDEX);
            request.id(String.valueOf(esModel.getSkuId()));
            String jsonString = JSON.toJSONString(esModel);
            request.source(jsonString, XContentType.JSON);
            bulkRequest.add(request);
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        return bulk.hasFailures();
    }
}
