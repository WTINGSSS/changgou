package com.czxy.pojo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_brand")
public class Brand implements Serializable {
    /**
     * 品牌id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 品牌名称
     */
    @Column(name = "brand_name")
    private String brandName;

    /**
     * 品牌图片地址
     */
    @Column(name = "logo")
    private String logo;

    private static final long serialVersionUID = 1L;
}