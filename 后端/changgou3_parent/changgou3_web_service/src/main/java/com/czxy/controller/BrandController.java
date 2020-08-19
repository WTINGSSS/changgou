package com.czxy.controller;

import com.czxy.pojo.Brand;
import com.czxy.service.BrandService;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/3/30 9:19
 * @description
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    @GetMapping("/category/{categoryId}")
    public BaseResult findBlist(@PathVariable("categoryId")Integer categoryId){
        List<Brand> blist = brandService.findBlist(categoryId);
        return BaseResult.ok("查询成功",blist);
    }
}
