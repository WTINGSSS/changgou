package com.czxy.controller;

import com.czxy.config.JwtProperties;
import com.czxy.pojo.Address;
import com.czxy.pojo.User;
import com.czxy.service.AddressService;
import com.czxy.utils.JwtUtils;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/5/3 19:38
 * @description
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private AddressService addressService;

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private HttpServletRequest request;

    @GetMapping
    public BaseResult findAlistByUserId(){
        String token = request.getHeader("authorization");
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token,jwtProperties.getPublicKey(),User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效或未登录");
        }
        List<Address> list = addressService.findAlistByUserId(loginUser.getId());
        return BaseResult.ok("查询成功",list);
    }

    @PostMapping
    public BaseResult addAddress(@RequestBody Address address){
        String token = request.getHeader("Authorization");
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token,jwtProperties.getPublicKey(),User.class);
        } catch (Exception e) {
            return BaseResult.error("token无效或失效");
        }
        address.setUserId(loginUser.getId().intValue());
        addressService.addAddress(loginUser,address);
        return BaseResult.ok("添加成功");

    }
}
