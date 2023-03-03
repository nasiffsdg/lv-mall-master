import com.alibaba.fastjson2.JSON;
import com.lv.mall.SearchApplication;
import com.lv.mall.config.ESConfig;
import com.lv.mall.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;


@Slf4j
@SpringBootTest(classes = SearchApplication.class)
public class EsTest {

    @Resource
    RestHighLevelClient client;

    @Test
    public void indexData() throws IOException {


        IndexRequest request = new IndexRequest("user");
        request.id("1");
//        request.source("ke", JSON.toJSONString(user));
        IndexResponse response = client.index(request, ESConfig.COMMON_OPPTIONS);
        log.info(String.valueOf(response.status()));
    }


    @Test
    public void SearchData() throws IOException {

        // 创建检索请请求
        SearchRequest request = new SearchRequest();
        // 指定索引
        request.indices("lv_user");
        // 指定检索条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("id","1"));

        request.source(builder);

        // 执行检索
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        // 获取数据
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            String source = hit.getSourceAsString();
            User user = JSON.parseObject(source, User.class);
            System.out.println(user);
        }

        // 获取分析信息
        for (Aggregation aggregation : response.getAggregations().asList()) {
            System.out.println(aggregation);
        }
        System.out.println(response);
    }

}
