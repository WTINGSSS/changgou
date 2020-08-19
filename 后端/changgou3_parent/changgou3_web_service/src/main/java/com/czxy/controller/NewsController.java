package com.czxy.controller;

import com.czxy.pojo.News;
import com.czxy.service.NewsService;
import com.czxy.vo.BaseResult;
import com.czxy.vo.NewsVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 庭前云落
 * @Date 2020/3/29 21:41
 * @description
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    @GetMapping
    public BaseResult findAll(NewsVo newsVo){
        PageInfo<News> pageInfo = this.newsService.findAll(newsVo);
        return BaseResult.ok("查询成功",pageInfo);
    }
}
