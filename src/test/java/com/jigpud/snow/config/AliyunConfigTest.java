package com.jigpud.snow.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : jigpud
 */
@Slf4j
@SpringBootTest
public class AliyunConfigTest {
    private final SMSConfig aliyunConfig;

    @Autowired
    AliyunConfigTest(SMSConfig aliyunConfig) {
        this.aliyunConfig = aliyunConfig;
    }

    @Test
    void testAliyunConfig() {
        log.debug("id: {}", aliyunConfig.getId());
        log.debug("secret: {}", aliyunConfig.getSecret());
    }
}
