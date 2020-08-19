package com.czxy.controller;

import com.czxy.config.JwtProperties;
import com.czxy.pojo.User;
import com.czxy.service.AuthService;
import com.czxy.utils.JwtUtils;
import com.czxy.vo.BaseResult;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 庭前云落
 * @Date 2020/3/27 8:44
 * @description
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public BaseResult login(@RequestBody User user) throws Exception {

        //验证码校验
        String code = stringRedisTemplate.opsForValue().get("login" + user.getUsername());
        if(code==null){
          return  BaseResult.error("验证码无效");
        }
        if(!code.equalsIgnoreCase(user.getCode())){
            return BaseResult.error("验证码错误");
        }
        User login = authService.login(user);
        if(login!=null){
          //生成Token
          String token = JwtUtils.generateToken(login,jwtProperties.getExpire(),jwtProperties.getPrivateKey());
         //登录成功删除验证码
         stringRedisTemplate.delete("login"+user.getUsername());
         return BaseResult.ok("登录成功").append("login",login).append("token",token);
        }else{
            return BaseResult.error("用户名或密码不匹配");
        }
    }
}
