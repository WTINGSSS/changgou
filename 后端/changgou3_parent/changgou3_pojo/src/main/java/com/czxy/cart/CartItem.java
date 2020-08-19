package com.czxy.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/4/17 13:09
 * @description
 */
@Data
public class CartItem {
    private Integer skuid;
    private Integer spuid;
    @JsonProperty("goods_name")
    private String goodsName;
    private Double price;
    private Integer count;//购买数量
    private Boolean checked;
    private String midlogo;
    @JsonProperty("spec_info_id_txt")
    private Map<String,String> specInfoIdTxt;
}
