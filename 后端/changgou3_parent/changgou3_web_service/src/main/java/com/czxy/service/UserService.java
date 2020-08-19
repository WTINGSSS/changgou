package com.czxy.service;

import com.czxy.pojo.User;

/**
 * @author 庭前云落
 * @Date 2020/3/20 21:49
 * @description
 */
public interface UserService {
    User findByUsername(String username);

    User findByMobile(String mobile);

    void register(User user);
}
