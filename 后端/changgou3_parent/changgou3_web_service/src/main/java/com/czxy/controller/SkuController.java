package com.czxy.controller;

import com.czxy.pojo.OneSkuResult;
import com.czxy.service.SkuService;
import com.czxy.vo.BaseResult;
import com.czxy.vo.ESData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/15 19:56
 * @description
 */
@RestController
@RequestMapping("/sku")
public class SkuController {

    @Resource
    private SkuService skuService;

    @GetMapping("/esData")
    public BaseResult<List<ESData>> findESData() {
        List<ESData> list = skuService.findESData();
        System.out.println(list);
        return BaseResult.ok("查询成功", list);
    }

    @GetMapping("/goods/{skuid}")
    public BaseResult<OneSkuResult> findSkuyById(@PathVariable("skuid") Integer skuid) {
        OneSkuResult sku = skuService.findSkuById(skuid);
        return BaseResult.ok("查询成功", sku);
    }

    /**
     * 更新数量
     */
    @PutMapping("/goods/{skuId}")
    public BaseResult updateSkuNum(@PathVariable("skuId")Integer skuId,@RequestParam("count")Integer count){
        skuService.updateSkuNum(skuId,count);
        return BaseResult.ok("更新成功");
    }
}
