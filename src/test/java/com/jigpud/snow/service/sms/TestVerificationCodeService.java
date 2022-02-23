package com.jigpud.snow.service.sms;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : jigpud
 */
@Slf4j
@SpringBootTest
public class TestVerificationCodeService {
    private final TestVerificationCodeServiceConfig testConfig;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    TestVerificationCodeService(TestVerificationCodeServiceConfig testConfig, VerificationCodeService verificationCodeService) {
        this.testConfig = testConfig;
        this.verificationCodeService = verificationCodeService;
    }

    @Test
    void testSendVerificationCode() {
        String phoneNumber = testConfig.getPhoneNumber();
        String verificationCode = verificationCodeService.newVerificationCode(phoneNumber);
        log.debug("phoneNumber: {}, verificationCode: {}", phoneNumber, verificationCode);
        boolean isSuccess = verificationCodeService.sendVerifyCode(phoneNumber, verificationCode);
        log.debug("isSuccess: {}", isSuccess);
    }

    @Test
    void testVerificationCodeExpiration() {
        String phoneNumber = testConfig.getPhoneNumber();
        String verificationCode = verificationCodeService.newVerificationCode(phoneNumber);
        log.debug("phoneNumber: {}, verificationCode: {}", phoneNumber, verificationCode);
        log.debug("is available now: {}, timestamp: {}",
                verificationCodeService.verify(phoneNumber, verificationCode),
                System.currentTimeMillis() / 1000);
        log.debug("sleep for 350s...");
        try {
            Thread.sleep(350 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("is available now: {}, timestamp: {}",
                verificationCodeService.verify(phoneNumber, verificationCode),
                System.currentTimeMillis() / 1000);
    }
}
