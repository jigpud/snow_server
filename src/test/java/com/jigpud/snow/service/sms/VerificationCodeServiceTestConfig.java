package com.jigpud.snow.service.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@ConfigurationProperties("test-verification-code-service")
public class VerificationCodeServiceTestConfig {
    private String phoneNumber;
}