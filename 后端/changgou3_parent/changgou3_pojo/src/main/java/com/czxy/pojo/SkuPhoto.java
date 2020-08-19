package com.czxy.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_sku_photo")
public class SkuPhoto implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * SPUID
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * 原图路径
     */
    @Column(name = "url")
    private String url;

    private static final long serialVersionUID = 1L;
}