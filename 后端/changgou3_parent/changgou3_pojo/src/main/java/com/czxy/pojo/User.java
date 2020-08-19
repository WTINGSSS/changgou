package com.czxy.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 庭前云落
 * @Date 2020/3/20 21:35
 * @description
 */
@Data
@Table(name="tb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

    /**
    * Email
    */
    private String email;

    /**
    * 手机号码
    */
    @Column(name = "mobile")
    private String mobile;

    /**
    * 昵称
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 头像
    */
    private String face;

    /**
    * 经验值
    */
    private Integer expriece;

    @Transient
    private String code;

    @Transient
    private String password_confirm;

    private static final long serialVersionUID = 1L;
}