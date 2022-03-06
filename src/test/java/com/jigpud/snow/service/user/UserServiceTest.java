package com.jigpud.snow.service.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : jigpud
 */
@Slf4j
@SpringBootTest
public class UserServiceTest {
    private final UserService userService;
    private final UserServiceTestConfig userServiceTestConfig;

    @Autowired
    UserServiceTest(UserService userService, UserServiceTestConfig userServiceTestConfig) {
        this.userService = userService;
        this.userServiceTestConfig = userServiceTestConfig;
    }

    @Test
    void testHaveUsernameIs() {
        String username = userServiceTestConfig.getUsername();
        log.debug("username: {}, exists: {}", username, userService.haveUsernameIs(username));
    }
}
