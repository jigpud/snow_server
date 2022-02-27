package com.jigpud.snow.repository.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Repository
public class TokenRepositoryImpl implements TokenRepository {
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    TokenRepositoryImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void saveRefreshToken(String userid, String refreshToken) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(keyOf(userid), refreshToken);
    }

    @Override
    public String getRefreshToken(String userid) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String refreshToken = operations.get(keyOf(userid));
        if (refreshToken != null) {
            return refreshToken;
        }
        return "";
    }

    @Override
    public void removeRefreshToken(String userid) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getAndDelete(keyOf(userid));
    }

    private String keyOf(String userid) {
        return "refreshToken:" + userid + "";
    }
}
