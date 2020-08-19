package com.czxy.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_address")
public class Address implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 收货人姓名
     */
    @Column(name = "shr_name")
    @JsonProperty("shr_name")
    private String shrName;

    /**
     * 收货人手机
     */
    @Column(name = "shr_mobile")
    @JsonProperty("shr_mobile")
    private String shrMobile;

    /**
     * 收货人省份
     */
    @Column(name = "shr_province")
    @JsonProperty("shr_province")
    private String shrProvince;

    /**
     * 收货人城市
     */
    @Column(name = "shr_city")
    @JsonProperty("shr_city")
    private String shrCity;

    /**
     * 收货人地区
     */
    @Column(name = "shr_area")
    @JsonProperty("shr_area")
    private String shrArea;

    /**
     * 收货人详情地址
     */
    @Column(name = "shr_address")
    @JsonProperty("shr_address")
    private String shrAddress;

    /**
     * 1:默认;0:不是
     */
    @Column(name = "isdefault")
    private Integer isdefault;

    private static final long serialVersionUID = 1L;
}