package com.czxy.client;

import com.czxy.pojo.OneSkuResult;
import com.czxy.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 庭前云落
 * @Date 2020/4/17 13:12
 * @description
 */
@FeignClient(value="cgwebservice",path = "/sku")
public interface SkuClient {
    @GetMapping("/goods/{skuid}")
    public BaseResult<OneSkuResult> findSkuById(@PathVariable("skuid") Integer skuid);
}
