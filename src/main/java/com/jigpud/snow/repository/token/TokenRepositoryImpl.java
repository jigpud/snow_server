package com.jigpud.snow.repository.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author : jigpud
 */
@Component
public class TokenRepositoryImpl implements TokenRepository {
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    TokenRepositoryImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void markLogin(String token, String userid, long expiration) {
        setIsLogin(token, userid, expiration, true);
    }

    @Override
    public void markLogout(String token, String userid) {
        setIsLogin(token, userid, 0, false);
    }

    @Override
    public void markAdmin(String token, String userid, long expiration) {
        setIsAdmin(token, userid, expiration, true);
    }

    @Override
    public void markUser(String token, String userid) {
        setIsAdmin(token, userid, 0, false);
    }

    @Override
    public boolean isLogin(String token, String userid) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        return token.equals(operations.get(keyOfLogin(userid)));
    }

    @Override
    public boolean isAdmin(String token, String userid) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        return token.equals(operations.get(keyOfAdmin(userid)));
    }

    private void setIsLogin(String token, String userid, long expiration, boolean isLogin) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        if (isLogin) {
            operations.set(keyOfLogin(userid), token, expiration, TimeUnit.SECONDS);
        } else {
            operations.getAndDelete(keyOfLogin(userid));
        }
    }

    private void setIsAdmin(String token, String userid, long expiration, boolean isAdmin) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        if (isAdmin) {
            operations.set(keyOfAdmin(userid), token, expiration, TimeUnit.SECONDS);
        } else {
            operations.getAndDelete(keyOfAdmin(userid));
        }
    }

    private String keyOfLogin(String userid) {
        return "login:" + userid;
    }

    private String keyOfAdmin(String userid) {
        return "admin:" + userid;
    }
}
