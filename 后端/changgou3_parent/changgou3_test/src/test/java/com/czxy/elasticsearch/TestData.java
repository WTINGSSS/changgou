package com.czxy.elasticsearch;

import com.czxy.Repository.ESBookRepository;
import com.czxy.TestApplication;
import com.czxy.vo.ESBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author 庭前云落
 * @Date 2020/4/13 20:51
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestData {

    @Resource
    private ESBookRepository esBookRepository;

    @Test
    public void demo01() {
        ESBook esBook = new ESBook(1L, "测试一", "1.jpg", 998f);
        esBookRepository.save(esBook);
    }

    @Test
    public void demo02() {
        ArrayList<ESBook> esBooks = new ArrayList<>();
        esBooks.add(new ESBook(2L, "测试二", "2.jpg", 456f));
        esBooks.add(new ESBook(3L, "测试三", "3.jpg", 290f));
        esBooks.add(new ESBook(4L, "测试四", "4.jpg", 100f));

        esBookRepository.saveAll(esBooks);
    }

    @Test
    public void demo03() {
        ESBook esBook = new ESBook(1L, "测试一改", "1111.jpg", 1998f);
        esBookRepository.save(esBook);
    }

    @Test
    public void demo04() {
        ESBook esBook = new ESBook();
        esBook.setId(1L);
        esBookRepository.delete(esBook);
    }
}
