package com.jigpud.snow.repository.sms;

import com.jigpud.snow.config.SMSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author : jigpud
 *
 */
@Component
public class VerificationCodeRepositoryImpl implements VerificationCodeRepository {
    private final SMSConfig smsConfig;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    VerificationCodeRepositoryImpl(SMSConfig smsConfig, StringRedisTemplate stringRedisTemplate) {
        this.smsConfig = smsConfig;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void saveVerificationCode(String phoneNumber, String verificationCode) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(keyOf(phoneNumber), verificationCode, smsConfig.getExpiration(), TimeUnit.SECONDS);
    }

    @Override
    public String getVerificationCode(String phoneNumber) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String verificationCode = operations.get(keyOf(phoneNumber));
        if (verificationCode != null && !verificationCode.isEmpty()) {
            return verificationCode;
        }
        return "";
    }

    @Override
    public void deleteVerificationCode(String phoneNumber, String verificationCode) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String currentVerificationCode = operations.getAndDelete(keyOf(phoneNumber));
        if (currentVerificationCode != null && !currentVerificationCode.isEmpty()) {
            operations.getAndDelete(keyOf(phoneNumber));
        }
    }

    private String keyOf(String phoneNumber) {
        return "verificationCode:" + phoneNumber;
    }
}
