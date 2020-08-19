package com.czxy.service;

import com.czxy.feign.UserFeign;
import com.czxy.pojo.User;
import com.czxy.utils.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 庭前云落
 * @Date 2020/3/27 8:41
 * @description
 */
@Service
public class AuthService {

    @Resource
    private UserFeign userFeign;

    public User login(User user){
        User result = userFeign.findByUsername(user);
        if(result==null){
        return null;
        }
        //校验密码
        boolean checkpw = BCrypt.checkpw(user.getPassword(), result.getPassword());
        if(checkpw){
           return result;
       }
       return null;
    }
}
