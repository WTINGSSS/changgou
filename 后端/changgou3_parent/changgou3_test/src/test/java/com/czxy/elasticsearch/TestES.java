package com.czxy.elasticsearch;

import com.czxy.TestApplication;
import com.czxy.vo.ESBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 庭前云落
 * @Date 2020/4/13 20:33
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestES {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void demo01() {
        //创建索引，会根据czxy56类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(ESBook.class);
    }

    @Test
    public void demo02() {
        //配置映射，会根据czxy56类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(ESBook.class);
    }

    @Test
    public void demo03() {
        //删除映射，可以根据类名或索引名删除
        elasticsearchTemplate.deleteIndex(ESBook.class);
    }
}
