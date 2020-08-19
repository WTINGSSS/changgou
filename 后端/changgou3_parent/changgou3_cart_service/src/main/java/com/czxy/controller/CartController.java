package com.czxy.controller;

import com.czxy.cart.Cart;
import com.czxy.config.JwtProperties;
import com.czxy.pojo.User;
import com.czxy.service.CartService;
import com.czxy.utils.JwtUtils;
import com.czxy.vo.BaseResult;
import com.czxy.vo.CartVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/4/17 13:12
 * @description
 */
@RestController
@RequestMapping("/carts")
public class CartController {
    @Resource
    private CartService cartService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtProperties jwtProperties;

    @PostMapping
    public BaseResult addCart(@RequestBody CartVo cartVo) {

        //1 获得用户信息
        // 1.1 获得token
        String token = request.getHeader("Authorization");
        // 1.2 解析token
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效或未登录");
        }

        //2 添加操作
        cartService.addCart(loginUser, cartVo);

        //3 提示
        return BaseResult.ok("添加成功");
    }

    @GetMapping
    public BaseResult findCartList() {
        String token = request.getHeader("Authorization");
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效或未登录");
        }
        Cart cart = cartService.findCartList(loginUser);
        System.out.println(cart);
        return BaseResult.ok("查询成功", cart.getData().values());
    }

    @PutMapping
    public BaseResult updateCart(@RequestBody List<CartVo> cartVoList) {
        String token = request.getHeader("Authorization");
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效或未登录");
        }

        try {
            cartService.updateCart(loginUser, cartVoList);
            return BaseResult.ok("成功");
        } catch (Exception e) {
            return BaseResult.error(e.getMessage());
        }

    }
}
