package com.czxy.feign;

import com.czxy.vo.BaseResult;
import com.czxy.vo.SearchSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/15 20:15
 * @description
 */
@FeignClient(value = "cgwebservice",path = "/sku")
@RequestMapping
public interface SkuFeign {

    @GetMapping("/esData")
    public BaseResult<List<SearchSku>> findESData();
}