package com.czxy.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 庭前云落
 * @Date 2020/3/20 21:35
 * @description
 */
@Data

public class User implements Serializable {

    private Integer id;


    private Date createdAt;


    private Date updatedAt;

    /**
    * Email
    */
    private String email;

    /**
    * 手机号码
    */

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


    private String code;


    private String password_confirm;

    private static final long serialVersionUID = 1L;
}