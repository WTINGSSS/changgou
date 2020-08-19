package com.czxy.service.Impl;

import com.alibaba.fastjson.JSON;
import com.czxy.cart.Cart;
import com.czxy.cart.CartItem;
import com.czxy.client.SkuClient;
import com.czxy.pojo.OneSkuResult;
import com.czxy.pojo.User;
import com.czxy.service.CartService;
import com.czxy.vo.BaseResult;
import com.czxy.vo.CartVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 庭前云落
 * @Date 2020/4/17 13:12
 * @description
 */
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SkuClient skuClient;

    /**
     * 给指定的用户，添加商品
     *
     * @param user
     * @param cartVo
     */
    public void addCart(User user, CartVo cartVo) {
        //查询sku
        BaseResult<OneSkuResult> baseResult = skuClient.findSkuById(cartVo.getSkuid());
        OneSkuResult oneSkuResult = baseResult.getData();

        //封装CarItem
        CartItem cartItem = new CartItem();
        cartItem.setSkuid( oneSkuResult.getSkuid() );
        cartItem.setSpuid( oneSkuResult.getSpuid() );
        cartItem.setGoodsName( oneSkuResult.getGoodsName() );
        cartItem.setPrice( oneSkuResult.getPrice() );
        cartItem.setCount( cartVo.getCount() );
        cartItem.setChecked( cartVo.getChecked() );
        cartItem.setMidlogo( oneSkuResult.getLogo().get("biglogo") );
        cartItem.setSpecInfoIdTxt( JSON.parseObject(oneSkuResult.getSpecInfo().get("id_txt"),Map.class));

        //从redis获得购物车，如果不存在需要创建一个新的,如果存在(字符串-->java对象)
        String cartJSON = stringRedisTemplate.opsForValue().get("cart" + user.getId());
        Cart cart = null;
        if(cartJSON != null){
            //将字符串转换成java对象
            cart = JSON.parseObject(cartJSON, Cart.class);
        } else {
            //创建新购物车
            cart = new Cart();
        }

        //添加购物车
        cart.addCart( cartItem );

        //保存reids
        cartJSON = JSON.toJSONString(cart);
        stringRedisTemplate.opsForValue().set("cart" + user.getId() , cartJSON);
    }


    //查询当前用户购物车
    public Cart findCartList(User user) {

        String cartString = this.stringRedisTemplate.opsForValue().get("cart" + user.getId());

        return JSON.parseObject(cartString, Cart.class);
    }

    //更新操作：如果数据存在 --》修改,如果数据不存在删除
    public void updateCart(User user, List<CartVo> cartVoList) {
        String cartStr = stringRedisTemplate.opsForValue().get( "cart" + user.getId());
        Cart cart = JSON.parseObject(cartStr, Cart.class);
        if (cart == null) {
            throw new RuntimeException("购物车不存在");
        }

        HashMap<Integer, CartVo> map = new HashMap<>();
        for (CartVo cartVo : cartVoList) {
            map.put(cartVo.getSkuid(), cartVo);
        }
        for (CartItem cartItem : cart.getData().values()) {
            CartVo cartVo = map.get(cartItem.getSkuid());
            if (cartVo != null) {
                //更新
                cart.updateCart(cartVo.getSkuid(), cartVo.getCount(), cartVo.getChecked());
            } else {

                cart.deleteCart(cartItem.getSkuid());
            }
        }
        //保存
        stringRedisTemplate.opsForValue().set("cart" + user.getId(), JSON.toJSONString(cart));
    }
}
