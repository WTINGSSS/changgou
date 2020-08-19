package com.czxy.controller;

import com.czxy.config.JwtProperties;
import com.czxy.pojo.User;
import com.czxy.service.OrderService;
import com.czxy.utils.JwtUtils;
import com.czxy.vo.BaseResult;
import com.czxy.vo.OrderVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 庭前云落
 * @Date 2020/5/6 15:28
 * @description
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private HttpServletRequest request;

    @PostMapping
    public BaseResult addOrder(@RequestBody OrderVo orderVo){
     String token = request.getHeader("Authorization");
     User loginUser = null;

        try {
            loginUser = JwtUtils.getObjectFromToken(token,jwtProperties.getPublicKey(),User.class);
        } catch (Exception e) {
            return BaseResult.error("token无效或失效");
        }

        Long sn = orderService.addOrder(loginUser,orderVo);
        return BaseResult.ok("下单成功").append("sn",sn+"");
    }
}
