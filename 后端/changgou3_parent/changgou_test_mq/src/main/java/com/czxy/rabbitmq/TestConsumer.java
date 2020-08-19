package com.czxy.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 庭前云落
 * @Date 2020/5/13 18:12
 * @description
 */
@Component
@RabbitListener(queues = "tingqianyunluo")
public class TestConsumer {

    @RabbitHandler
    public void demo01(String message){
        System.out.println("浩浩星河谁为主，万古青史我来书！"+message);
    }
}
