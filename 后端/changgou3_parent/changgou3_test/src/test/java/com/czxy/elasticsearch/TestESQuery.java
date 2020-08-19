package com.czxy.elasticsearch;

import com.czxy.Repository.ESBookRepository;
import com.czxy.TestApplication;
import com.czxy.vo.ESBook;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 庭前云落
 * @Date 2020/4/14 20:13
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestESQuery {

    @Resource
    private ESBookRepository esBookRepository;

    //查询：title为"测试"
    @Test
    public void demo01() {
        //查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //关键字查询 match
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "测试"));
        //查询
        Page<ESBook> page = esBookRepository.search(queryBuilder.build());
        //处理结果
        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        for (ESBook esBook : page) {
            System.out.println(esBook);
        }
    }

    //多条件查询,查询title为"测试",不含"二"
    @Test
    public void demo02() {
        //查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //查询条件 -- 多条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //条件1:必须
        boolQueryBuilder.must(QueryBuilders.matchQuery("title", "测试"));
        //条件2:不需要
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("title", "三"));

        queryBuilder.withQuery(boolQueryBuilder);
        //查询
        Page<ESBook> page = esBookRepository.search(queryBuilder.build());

        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        for (ESBook esBook : page) {
            System.out.println(esBook);
        }
    }

    //查询 title包含"二"、"三"的相关数据
    @Test
    public void demo03() {
        //查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.matchQuery("title", "三"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("title", "二"));
        queryBuilder.withQuery(boolQueryBuilder);

        //查询
        Page<ESBook> page = esBookRepository.search(queryBuilder.build());
        for (ESBook esBook : page) {
            System.out.println(esBook);
        }
    }

    //查询价格为456
    @Test
    public void demo04() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery("price", 456));
        Page<ESBook> page = esBookRepository.search(queryBuilder.build());
        System.out.println("总条数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        for (ESBook esBook : page) {
            System.out.println(esBook);
        }
    }

    //查询价格在 50-300之间
    @Test
    public void demo05() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.rangeQuery("price").gte(50).lte(300));

        Page<ESBook> page = esBookRepository.search(queryBuilder.build());

        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        for (ESBook esBook : page) {
            System.out.println(esBook);
        }
    }

    @Test
    public void demo06() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        int pageNum = 1;
        int pageSize = 2;

        queryBuilder.withPageable(PageRequest.of(pageNum, pageSize));

        Page<ESBook> page = esBookRepository.search(queryBuilder.build());

        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        for (ESBook esBook : page) {
            System.out.println(esBook);
        }
    }

    @Test
    public void demo07() {
        //查询条件构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //查询条件
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        //查询
        Page<ESBook> page = esBookRepository.search(queryBuilder.build());

        System.out.println("总条数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());

        for (ESBook esBook : page) {
            System.out.println(esBook);
        }
    }
}









