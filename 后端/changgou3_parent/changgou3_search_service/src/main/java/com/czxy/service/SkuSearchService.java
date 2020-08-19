package com.czxy.service;

import com.czxy.vo.SearchVo;

import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/4/15 23:51
 * @description
 */
public interface SkuSearchService {
    Map search(SearchVo searchVo);
}
