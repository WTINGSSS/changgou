package com.czxy.service;

import com.czxy.pojo.User;
import com.czxy.vo.OrderVo;

/**
 * @author 庭前云落
 * @Date 2020/5/5 15:04
 * @description
 */
public interface OrderService {
    Long addOrder(User loginUser, OrderVo orderVo);
}
