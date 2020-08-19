package com.czxy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_spu")
public class Spu implements Serializable {
    /**
     * spu id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "spu_name")
    private String spuName;

    /**
     * 子标题
     */
    @Column(name = "spu_subname")
    private String spuSubname;

    @Column(name = "logo")
    private String logo;

    /**
     * 1级类目id
     */
    @Column(name = "cat1_id")
    private Integer cat1Id;

    /**
     * 2级类目id
     */
    @Column(name = "cat2_id")
    private Integer cat2Id;

    /**
     * 3级类目id
     */
    @Column(name = "cat3_id")
    private Integer cat3Id;

    /**
     * 商品所属品牌id
     */
    @Column(name = "brand_id")
    private Integer brandId;

    @Transient
    private Brand brand;
    /**
     * 审核时间
     */
    @Column(name = "check_time")
    private Date checkTime;

    /**
     * 审核状态，0：未审核，1：已通过，2：未通过
     */
    @Column(name = "check_status")
    private Integer checkStatus;

    /**
     * 商品价格，单位：元
     */
    @Column(name = "price")
    private Double price;

    /**
     * 是否上架 0 未上架 1 上架
     */
    @Column(name = "is_on_sale")
    private Integer isOnSale;

    /**
     * 上架时间
     */
    @Column(name = "on_sale_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onSaleTime;

    /**
     * 删除时间
     */
    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deletedAt;

    /**
     * 重量
     */
    @Column(name = "weight")
    private Double weight;

    /**
     * 商品详情
     */
    @Column(name = "description")
    private String description;

    /**
     * 包装
     */
    @Column(name = "packages")
    private String packages;

    /**
     * 售后
     */
    @Column(name = "aftersale")
    private String aftersale;

    /**
     * 规格列表
     */
    @Column(name = "spec_list")
    private String specList;

    /**
     * 添加时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 最后修改时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}