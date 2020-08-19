package com.czxy;

import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @author 庭前云落
 * @Date 2020/3/24 11:32
 * @description
 */
public class TestJava {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("WTINGSSS", "闲看庭前云落");
        String wtingsss = jedis.get("WTINGSSS");
        System.out.println(wtingsss);
        jedis.close();
    }


}
