package com.czxy.service;

import com.czxy.pojo.Address;
import com.czxy.pojo.User;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/5/3 19:32
 * @description
 */
public interface AddressService {
    List<Address> findAlistByUserId(Integer id);

    void addAddress(User loginUser, Address address);
}
