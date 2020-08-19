package com.czxy.service;

import com.czxy.cart.Cart;
import com.czxy.pojo.User;
import com.czxy.vo.CartVo;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/17 13:13
 * @description
 */
public interface CartService {
    void addCart(User loginUser, CartVo cartVo);

    Cart findCartList(User loginUser);

    void updateCart(User loginUser, List<CartVo> cartVoList);
}
