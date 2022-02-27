package com.jigpud.snow.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : jigpud
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "audience")
public class AudienceConfig {
    private String secret;
    private String name;
    private Long expire;
}
