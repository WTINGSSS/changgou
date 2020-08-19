package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 庭前云落
 * @Date 2020/3/20 17:08
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CGWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGWebServiceApplication.class,args);
    }
}
