package com.jigpud.snow.service.token;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author : jigpud
 */
@Slf4j
@SpringBootTest
public class TokenServiceTest {
    private final TokenService tokenService;

    @Autowired
    TokenServiceTest(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Test
    void testCreateToken() {
        String userid = "123456789";
        String refreshToken = tokenService.createRefreshToken(userid);
        String token = tokenService.createToken(refreshToken);
        log.debug("refreshToken   ===> {}", refreshToken);
        log.debug("token          ===> {}", token);
        log.debug("userid         ===> {}", tokenService.getUserid(token));
        log.debug("isExpiration   ===> {}", tokenService.isExpiration(token));
        log.debug("isAvailable    ===> {}", tokenService.verify(token));
    }

    @Test
    void testTokenExpiration() {
        String userid = "123456789";
        String token = tokenService.createToken(userid);
        log.debug("expiration: {}, now: {}", tokenService.getExpiration(token), new Date());
    }
}
