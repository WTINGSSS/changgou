package com.czxy.elasticsearch;

import com.czxy.Repository.ESBookRepository;
import com.czxy.TestApplication;
import com.czxy.vo.ESBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author 庭前云落
 * @Date 2020/4/13 21:01
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestESFind {
    @Resource
    private ESBookRepository esBookRepository;

    @Test
    public void demo01() {
        Iterable<ESBook> iterable = esBookRepository.findAll();
        Iterator<ESBook> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            ESBook esBook = iterator.next();
            System.out.println(esBook);
        }
    }

    @Test
    public void demo02() {
        Optional<ESBook> optional = esBookRepository.findById(2L);
        ESBook esBook = optional.get();
        System.out.println(esBook);
    }

    @Test
    public void demo03() {
        List<ESBook> list = esBookRepository.findByTitle("测试二");
        System.out.println(list);
    }

    @Test
    public void demo04() {
        List<ESBook> list = esBookRepository.findByPriceBetween(50f, 300f);
        System.out.println(list);
    }

    @Test
    public void demo05(){
        List<ESBook> list = esBookRepository.findByPriceGreaterThanEqual(290f);
        System.out.println(list);
    }
    @Test
    public void demo06(){
        List<ESBook> list = esBookRepository.findByPriceBetweenOrderByImagesDesc(50f,300f);
        System.out.println(list);
    }
}
