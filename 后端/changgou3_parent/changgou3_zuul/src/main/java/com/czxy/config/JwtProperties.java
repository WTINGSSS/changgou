package com.czxy.config;

import com.czxy.utils.RasUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author 庭前云落
 * @Date 2020/3/29 13:29
 * @description
 */
@Data
@ConfigurationProperties(prefix = "sc.jwt")
@Component
public class JwtProperties {
    private String secret; // 密钥

    private String pubKeyPath;// 公钥

    private String priKeyPath;// 私钥

    private int expire;// token过期时间

    private PublicKey publicKey; // 公钥

    private PrivateKey privateKey; // 私钥

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    @PostConstruct
    public void init(){
        try {
            File pubFile = new File(this.pubKeyPath);
            File priFile = new File(this.priKeyPath);
            if( !pubFile.exists() || !priFile.exists()){
                RasUtils.generateKey( this.pubKeyPath ,this.priKeyPath , this.secret);
            }
            this.publicKey = RasUtils.getPublicKey( this.pubKeyPath );
            this.privateKey = RasUtils.getPrivateKey( this.priKeyPath );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
