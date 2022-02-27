package com.jigpud.snow.util.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : jigpud
 * 使用token来认证
 */
public class JWTToken implements AuthenticationToken {
    private final String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
