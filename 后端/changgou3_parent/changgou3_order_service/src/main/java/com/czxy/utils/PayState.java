package com.czxy.utils;

import lombok.Getter;

/**
 * @author 庭前云落
 * @Date 2020/5/8 17:40
 * @description
 */
@Getter
public enum PayState {

    NOT_PAY(0,"未支付"),SUCCESS(1,"支付成功"),CLOSED(2,"已关闭"),PAY_ERROR(3,"支付失败");

    PayState(int code,String desc) {
        this.code = code;
        this.desc = desc;
    }
    private int code;           //自定义编码
    private String desc;        //描述信息
}
