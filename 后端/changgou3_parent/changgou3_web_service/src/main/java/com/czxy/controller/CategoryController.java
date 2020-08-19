package com.czxy.controller;

import com.czxy.pojo.Category;
import com.czxy.service.CategoryService;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/3/29 23:33
 * @description
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping
    public BaseResult findClist(){
        List<Category> list = categoryService.findClist();
        return BaseResult.ok("查询成功",list);
    }
}
