package com.czxy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/3/29 15:17
 * @description
 */
@Data
@ConfigurationProperties(prefix = "sc.filter")
@Component
public class FilterProperties {
    //允许访问路径集合
    private List<String> allowPaths;
}
