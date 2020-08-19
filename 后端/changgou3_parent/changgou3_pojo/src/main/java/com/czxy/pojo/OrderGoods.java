package com.czxy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "tb_order_good")
@NoArgsConstructor
@AllArgsConstructor
public class OrderGoods implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "sn")
    private Long sn;


    /**
     * SPUID
     */
    @Column(name = "spu_id")
    private Integer spuId;

    @Transient
    private Sku sku;

    /**
     * SKUID
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * 购买数量
     */
    @Column(name = "`number`")
    private Integer number;

    /**
     * 规格列表
     */
    @Column(name = "spec_list")
    private String specList;

    /**
     * 商品名称
     */
    @Column(name = "sku_name")
    private String skuName;

    /**
     * 图片
     */
    @Column(name = "url")
    private String url;

    /**
     * 价格
     */
    @Column(name = "price")
    private Double price;

    private static final long serialVersionUID = 1L;
}