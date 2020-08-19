package com.czxy.service;

import com.czxy.pojo.News;
import com.czxy.vo.NewsVo;
import com.github.pagehelper.PageInfo;

/**
 * @author 庭前云落
 * @Date 2020/3/29 21:42
 * @description
 */
public interface NewsService {
    PageInfo<News> findAll(NewsVo newsVo);
}
