package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 庭前云落
 * @Date 2020/3/20 15:49
 * @description
 */
@SpringBootApplication
@EnableEurekaServer
public class CGEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGEurekaApplication.class,args);
    }
}
