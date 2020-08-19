package com.czxy.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/4/15 21:39
 * @description
 */
@Data
public class SearchVo extends PageRequest {
    private String keyword;                 // 关键字搜索，预留
    private Integer catId;                  // 3 级类目
    private Integer brandId;                // 品牌
    private Map<String,String> specList;    // 规格选项列表
    private Double minPrice;                //最低价格
    private Double maxPrice;                //最高价格
    private Integer limit;                  //限制条数
}
