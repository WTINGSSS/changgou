package com.czxy.listener;

import com.czxy.service.Impl.OrderServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 庭前云落
 * @Date 2020/5/14 20:57
 * @description
 */
@Component
@RabbitListener(queues = "order_pay")
public class OrderPayListener {

    @Resource
    private OrderServiceImpl orderServiceImpl;

    @RabbitHandler
    public void updateOrderStatus(String sn){
        orderServiceImpl.updateOrderStatus(sn, "1");
    }
}
