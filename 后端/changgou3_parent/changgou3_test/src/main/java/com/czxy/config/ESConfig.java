package com.czxy.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author 庭前云落
 * @Date 2020/4/13 20:38
 * @description
 */
@Configuration
public class ESConfig {
    @PostConstruct
    public void init(){
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }
}
