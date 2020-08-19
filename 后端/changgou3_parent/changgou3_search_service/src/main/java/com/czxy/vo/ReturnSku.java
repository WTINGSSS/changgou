package com.czxy.vo;

import lombok.Data;

/**
 * @author 庭前云落
 * @Date 2020/4/15 21:38
 * @description
 */
@Data
public class ReturnSku {
    private Integer id;
    private String goodsName;           //sku名称
    private Double price;               //价格
    private String midlogo;             //sku logo
    private Integer commentCount;      //sku的评论

}
