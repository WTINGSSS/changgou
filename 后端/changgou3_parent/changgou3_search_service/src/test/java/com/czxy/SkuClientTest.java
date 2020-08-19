package com.czxy;

import com.czxy.feign.SkuFeign;
import com.czxy.repository.SkuRepository;
import com.czxy.vo.BaseResult;
import com.czxy.vo.SearchSku;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/16 21:10
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchServiceApplication.class)
public class SkuClientTest {
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private SkuFeign skuFeign;

    @Resource
    private SkuRepository skuRepository;

    @Test
    public void demo03() {
        // 创建索引
        this.elasticsearchTemplate.createIndex(SearchSku.class);
        // 配置映射
        this.elasticsearchTemplate.putMapping(SearchSku.class);
    }

    @Test
    public void demo01() {
        BaseResult<List<SearchSku>> esData = skuFeign.findESData();
        List<SearchSku> list = esData.getData();
        skuRepository.saveAll(list);
    }


    @Test
    public void demo02() {
        BaseResult<List<SearchSku>> baseResult = skuFeign.findESData();
        List<SearchSku> list = baseResult.getData();
        System.out.println(list);

    }

    @Test
    public void demo04() {
        //删除映射，可以根据类名或索引名删除
        elasticsearchTemplate.deleteIndex("skus");
    }
}
