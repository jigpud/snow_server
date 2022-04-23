package com.jigpud.snow.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : jigpud
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "qiniu")
public class QiniuConfig {
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String domain;
    private Long uploadTokenExpiration;
}
