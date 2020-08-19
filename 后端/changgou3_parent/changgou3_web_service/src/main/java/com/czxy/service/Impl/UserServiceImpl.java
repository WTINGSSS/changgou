package com.czxy.service.Impl;

import com.czxy.mapper.UserMapper;
import com.czxy.pojo.User;
import com.czxy.service.UserService;
import com.czxy.utils.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 庭前云落
 * @Date 2020/3/20 21:47
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 通过用户名查询
     */
    public User findByUsername(String username){
        return userMapper.findByUsername(username);
    }

    /**
     * 校验手机号
     */
    public User findByMobile(String mobile){
       User result = userMapper.findByMybile(mobile);
       if(result!=null){
           return result;
       }
        return null;
    }

    /**
     * 用户注册
     */
    public void register(User user){
        //密码加密
        String password = BCrypt.hashpw(user.getPassword());
        user.setPassword(password);

        //注册时间
        user.setCreatedAt(new Date());
        user.setUpdatedAt(user.getCreatedAt());

        userMapper.insert(user);
    }
}
