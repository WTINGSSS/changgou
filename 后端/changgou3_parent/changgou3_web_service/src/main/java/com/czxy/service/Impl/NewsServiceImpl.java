package com.czxy.service.Impl;

import com.czxy.mapper.NewsMapper;
import com.czxy.pojo.News;
import com.czxy.service.NewsService;
import com.czxy.vo.NewsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/3/29 21:41
 * @description
 */
@Service
public class NewsServiceImpl implements NewsService {

     @Resource
     private NewsMapper newsMapper;

     public PageInfo<News> findAll(NewsVo newsVo){
         PageHelper.startPage(newsVo.getPageNum(),newsVo.getPageSize());

         //排序 根据排序方式进行排序
         Example example = new Example(News.class);
         if("asc".equalsIgnoreCase(newsVo.getSortWay())){
          example.setOrderByClause("created_at asc");
         }else{
             example.setOrderByClause("created_at desc");
         }

         List<News> nlist = this.newsMapper.selectByExample(example);
         return new PageInfo<>(nlist);
     }
}

