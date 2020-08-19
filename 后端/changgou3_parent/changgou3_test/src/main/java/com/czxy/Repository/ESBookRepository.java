package com.czxy.Repository;

import com.czxy.vo.ESBook;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/13 20:49
 * @description
 */
public interface ESBookRepository extends ElasticsearchRepository<ESBook, Long> {
    //实例1:根据title查询
    public List<ESBook> findByTitle(String title);
     //实例2：区间查询，价格50-300
    public List<ESBook> findByPriceBetween(Float start, Float end);

    public List<ESBook> findByPriceGreaterThanEqual(Float price);

    public List<ESBook> findByPriceBetweenOrderByImagesDesc(Float start, Float end);


}
