package com.czxy.feign;

import com.czxy.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 庭前云落
 * @Date 2020/5/5 15:01
 * @description
 */
@FeignClient(value = "cgwebservice",path = "/sku")
@RequestMapping
public interface SkuFeign {

    @PutMapping("/goods/{skuId}")
    public BaseResult updateSkuNum(@PathVariable("skuId")Integer skuId, @RequestParam("count")Integer count);
}
