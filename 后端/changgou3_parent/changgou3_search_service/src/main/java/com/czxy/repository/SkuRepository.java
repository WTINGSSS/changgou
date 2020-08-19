package com.czxy.repository;

import com.czxy.vo.SearchSku;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 庭前云落
 * @Date 2020/4/15 20:38
 * @description
 */
public interface SkuRepository extends ElasticsearchRepository<SearchSku,Long> {
}
