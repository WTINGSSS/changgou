package com.czxy.service.Impl;

import com.alibaba.fastjson.JSON;
import com.czxy.cart.Cart;
import com.czxy.cart.CartItem;
import com.czxy.feign.SkuFeign;
import com.czxy.mapper.AddressMapper;
import com.czxy.mapper.OrderGoodsMapper;
import com.czxy.mapper.OrderMapper;
import com.czxy.pojo.Address;
import com.czxy.pojo.Order;
import com.czxy.pojo.OrderGoods;
import com.czxy.pojo.User;
import com.czxy.service.OrderService;
import com.czxy.utils.IdWorker;
import com.czxy.vo.OrderVo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;

/**
 * @author 庭前云落
 * @Date 2020/5/5 15:04
 * @description
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private IdWorker idWorker;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private OrderGoodsMapper orderGoodsMapper;

    @Resource
    private SkuFeign skuFeign;

    /**
     * 封装订单对象对应数据，保存
     *
     * @param user
     * @param orderVo
     * @return
     */
    @GlobalTransactional
    public Long addOrder(User user, OrderVo orderVo) {
        //1 创建订单对象
        Order order = new Order();

        //1.1 序列号（采用雪花算法）
        long sn = idWorker.nextId();
        order.setSn(sn);

        //1.2 封装收获人信息（冗余字段，方便查询）
        Address address = addressMapper.selectByPrimaryKey(orderVo.getAddressId());
        order.setShrName(address.getShrName());
        order.setShrMobile(address.getShrMobile());
        order.setShrProvince(address.getShrProvince());
        order.setShrCity(address.getShrCity());
        order.setShrArea(address.getShrArea());
        order.setShrAddress(address.getShrAddress());

        //1.3 系统自动生成数据
        order.setCreatedAt(new Date());
        ;
        order.setUpdatedAt(order.getCreatedAt());
        order.setStatus(0);     //此处可以使用常量替换

        //1.4 用户信息
        order.setUserId(user.getId());

        //2 获得购物车
        String cartStr = stringRedisTemplate.opsForValue().get("cart" + user.getId());
        Cart cart = JSON.parseObject(cartStr, Cart.class);

        double total = 0;
        //2.1  处理选中购物项(购买具体商品)，需要转换成 OrderGoods
        Iterator<CartItem> it = cart.getData().values().iterator();
        while (it.hasNext()) {
            CartItem cartItem = it.next();
            // 选中
            if (cartItem.getChecked()) {
                // 2.1.1 构建OrderGoods对象
                OrderGoods orderGoods = new OrderGoods();
                // 2.1.2  填充数据：订单的序列号、商品信息
                orderGoods.setSn(sn);
                orderGoods.setSkuId(cartItem.getSkuid());
                orderGoods.setSpuId(cartItem.getSpuid());
                orderGoods.setNumber(cartItem.getCount());
                orderGoods.setSpecList(JSON.toJSONString(cartItem.getSpecInfoIdTxt()));
                orderGoods.setSkuName(cartItem.getGoodsName());
                orderGoods.setUrl(cartItem.getMidlogo());
                orderGoods.setPrice(cartItem.getPrice());

                // 2.1.3 保存
                orderGoodsMapper.insert(orderGoods);
                // 2.1.4 修改库存
                skuFeign.updateSkuNum(cartItem.getSkuid(), cartItem.getCount());
                // 2.1.5 维护购物车数据（移除选中项）--使用迭代器
                it.remove();
                // 2.1.6 计算勾选的总价
                total += (cartItem.getCount() * cartItem.getPrice());
            }
        }

        //2.2 订单总价（从购物车总价）-- 所有勾选综合
        order.setTotalPrice(total);

        //3 保存订单
        orderMapper.insert(order);

        //4 将购物车对象写入redis
        stringRedisTemplate.opsForValue().set("cart" + user.getId(), JSON.toJSONString(cart));

        //5 将订单的序列号返回
        return sn;
    }

    /**
     * 修改指定订单号的状态
     * @param sn
     * @param status
     */
    public void updateOrderStatus(String sn, String status) {
        orderMapper.updateOrderStatus( sn , status );
    }
}
