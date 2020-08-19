package com.czxy.vo;

import lombok.Data;

/**
 * @author 庭前云落
 * @Date 2020/3/29 21:38
 * @description
 */
@Data
public class PageRequest {

    private Integer pageNum;
    private Integer pageSize;
    //限制条数
    private Integer limit;
    //偏移
    private Integer offset;
    //排序字段
    private String sortBy;
    //排序方式
    private String sortWay;
}
