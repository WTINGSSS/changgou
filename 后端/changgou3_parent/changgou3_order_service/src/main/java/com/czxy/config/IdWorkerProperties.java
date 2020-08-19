package com.czxy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liangtong.
 */
@ConfigurationProperties(prefix = "sc.worker")
@Data
public class IdWorkerProperties {

    private Long  workerId;

    private Long datacenterId;


}
