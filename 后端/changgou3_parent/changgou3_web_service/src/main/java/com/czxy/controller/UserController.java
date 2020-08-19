package com.czxy.controller;

import com.czxy.pojo.User;
import com.czxy.service.UserService;
import com.czxy.vo.BaseResult;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 庭前云落
 * @Date 2020/3/20 21:52
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/checkusername")
    public BaseResult check(@RequestBody User user) {
        User result = userService.findByUsername(user.getUsername());
        if (result != null) {
            return BaseResult.error("用户已经存在");
        } else {
            return BaseResult.ok("用户名可用");
        }
    }

    @PostMapping("/checkmobile")
    public BaseResult findByMobile(@RequestBody User user) {

        User result = userService.findByMobile(user.getMobile());
        if (result != null) {
            return BaseResult.error("该手机号已被注册");
        } else {
            return BaseResult.ok("手机号码可用");
        }
    }

    @PostMapping("/register")
    public BaseResult register(@RequestBody User user) {
        //服务端校验
        User result = userService.findByUsername(user.getUsername());
        if (result != null) {
            return BaseResult.error("用户名已经存在");
        }

        result = userService.findByMobile(user.getMobile());
        if (result != null) {
            return BaseResult.error("该手机号已被注册");
        }

        //验证码
        String code = stringRedisTemplate.opsForValue().get("sms_register" + user.getMobile());

        if (code == null) {
            return BaseResult.error("验证码无效");
        }
        if (!code.equalsIgnoreCase(user.getCode())) {
            return BaseResult.error("验证码不正确");
        }
        //注册
        userService.register(user);
        // 注册完成后删除redis中的验证码
        stringRedisTemplate.delete("sms_register" + user.getMobile());
        return BaseResult.ok("注册成功");
    }

    @PostMapping("/findByUsername")
    public User findByUsername(@RequestBody User user) {
        User result = userService.findByUsername(user.getUsername());
        return result;
    }
}












