package com.czxy.controller;

import com.czxy.pojo.Specification;
import com.czxy.service.SpecificationService;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/7 17:57
 * @description
 */
@RestController
@RequestMapping("/specifications")
public class SpecificationController {

    @Resource
    private SpecificationService specificationService;
    @GetMapping("/category/{categoryId}")
    public BaseResult findAll(@PathVariable("categoryId") Integer categoryId){
        //查询
        List<Specification> list = specificationService.findAll(categoryId);
        //返回
        return BaseResult.ok("查询成功", list );
    }


}
