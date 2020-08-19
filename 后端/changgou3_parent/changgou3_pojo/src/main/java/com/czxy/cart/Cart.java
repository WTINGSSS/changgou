package com.czxy.cart;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/4/17 13:11
 * @description
 */
@Data
public class Cart {
    //存放多个购物项，Map适合快速查询
    private Map<Integer, CartItem> data = new HashMap<>();
    //存放总价
    private Double total;

    public Double getTotal() {
        double sum = 0.0;
        for (CartItem cartItem : data.values()) {
            //只统计勾选的价格
            if (cartItem.getChecked()) {
                sum += (cartItem.getPrice() * cartItem.getCount());
            }
        }
        return sum;
    }

    //将物品添加到购物车
    public void addCart(CartItem cartItem) {
        CartItem temp = data.get(cartItem.getSkuid());
        //如果购物车中没有档期物品直接添加
        if (temp == null) {
            data.put(cartItem.getSkuid(), cartItem);
        } else {
            //如果购物车中有当前购物项,将更新购物车数量
            temp.setCount(cartItem.getCount() + temp.getCount());
        }
    }


    //更新数据
    public void updateCart(Integer skuid, Integer count, Boolean checked) {
        CartItem temp = data.get(skuid);
        if (temp != null) {
            temp.setCount(count);
            temp.setChecked(checked);
        }
    }

    //从购物车中移除
    public void deleteCart(Integer skuid) {
        data.remove(skuid);
    }
}
