package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : jigpud
 */
@Slf4j
@SpringBootTest
public class UserMapperTest {
    private final UserMapper userMapper;

    @Autowired
    UserMapperTest(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    void testPagination() {
        Page<User> page = new Page<>(1, 2);
        Page<User> res = userMapper.selectPage(page, new QueryWrapper<>());
        log.debug("size: {}", res.getSize());
        log.debug("total: {}", res.getTotal());
        log.debug("pages: {}", res.getPages());
        log.debug("current: {}", res.getCurrent());
        for (User user : res.getRecords()) {
            log.debug("user: {}", user);
        }
    }
}
