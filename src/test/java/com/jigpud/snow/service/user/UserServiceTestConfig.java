package com.jigpud.snow.service.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : jigpud
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Configuration
@ConfigurationProperties(prefix = "user-service-test")
public class UserServiceTestConfig {
    private String username;
}
