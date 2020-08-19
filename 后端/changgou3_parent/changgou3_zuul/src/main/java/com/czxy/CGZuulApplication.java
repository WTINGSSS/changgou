package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 庭前云落
 * @Date 2020/3/20 16:04
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class CGZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGZuulApplication.class,args);
    }
}
