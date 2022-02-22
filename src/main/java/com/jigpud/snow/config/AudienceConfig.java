package com.jigpud.snow.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : jigpud
 */
@Data
@Component
@ConfigurationProperties(prefix = "audience")
public class AudienceConfig {
    private String secret;
    private String name;
    private long expire;
}
