package com.czxy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_sku")
public class Sku implements Serializable {
    /**
     * sku id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "stock")
    private Integer stock;

    /**
     * spu id
     */
    @Column(name = "spu_id")
    private Integer spuId;

    @Transient
    private Spu spu;
    /**
     * 商品标题
     */
    @Column(name = "sku_name")
    private String skuName;

    /**
     * 商品的图片，多个图片以‘,’分割
     */
    @Column(name = "images")
    private String images;

    /**
     * 销售价格，单位为分
     */
    @Column(name = "price")
    private Double price;

    @Column(name = "spec_info_id_list")
    private String specInfoIdList;

    /**
     * sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序
     */
    @Column(name = "spec_info_id_txt")
    private String specInfoIdTxt;

    /**
     * 添加时间
     */
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /**
     * 最后修改时间
     */
    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}