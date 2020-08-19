package com.czxy.controller;

import com.czxy.service.SkuSearchService;
import com.czxy.vo.BaseResult;
import com.czxy.vo.SearchVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/4/15 21:39
 * @description
 */
@RestController
@RequestMapping("/sku")
public class SkuSearchController {

    @Resource
    private SkuSearchService skuSearchService;

    @PostMapping("/search")
    public BaseResult findSkus(@RequestBody SearchVo searchVo) {
        if (searchVo.getCatId() == null) {
            return BaseResult.error("分类不能为空");
        }
        Map search = skuSearchService.search(searchVo);
        System.out.println(search);
        return BaseResult.ok("查询成功", search);
    }
}
