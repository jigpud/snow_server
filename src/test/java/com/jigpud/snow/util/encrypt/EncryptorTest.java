package com.jigpud.snow.util.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : jigpud
 */
@Slf4j
@SpringBootTest
public class EncryptorTest {
    @Test
    void testHacSHA256Encrypt() {
        String message = "Hello world!";
        String salt = "salt";
        String encryption = Encryptor.hmacSHA256Encrypt(message, salt);
        log.debug(encryption);
    }

    @Test
    void testUUID() {
        log.debug("uuid: {}", Encryptor.uuid());
    }
}
