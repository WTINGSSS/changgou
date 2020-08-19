package com.czxy.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_impression")
public class Impression implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 印象
     */
    @Column(name = "title")
    private String title;

    @Column(name = "`count`")
    private Integer count;

    @Column(name = "spu_id")
    private Integer spuId;

    @Column(name = "sku_id")
    private Integer skuId;

    private static final long serialVersionUID = 1L;
}