package com.czxy.service;

import com.czxy.pojo.OneSkuResult;
import com.czxy.vo.ESData;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/15 9:58
 * @description
 */
public interface SkuService {
    List<ESData> findESData();

    OneSkuResult findSkuById(Integer skuid);

    void updateSkuNum(Integer skuId, Integer count);
}
