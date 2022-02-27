package com.jigpud.snow.config;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author : jigpud
 * 阿里云短信验证码配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.sms")
public class SMSConfig {
    private String id;
    private String secret;
    private String endpoint;
    private String signName;
    private String templateCode;
    private int verificationCodeLength;
    private long expiration;

    @Bean
    Client aliyunSmsClient(SMSConfig aliyunConfig) throws Exception {
        Config config = new Config()
                .setAccessKeyId(aliyunConfig.getId())
                .setAccessKeySecret(aliyunConfig.getSecret())
                .setEndpoint(aliyunConfig.getEndpoint());
        return new Client(config);
    }
}
