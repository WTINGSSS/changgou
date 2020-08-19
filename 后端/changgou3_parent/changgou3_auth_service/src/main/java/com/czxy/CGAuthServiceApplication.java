package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 庭前云落
 * @Date 2020/3/26 11:12
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CGAuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGAuthServiceApplication.class,args);
    }
}
