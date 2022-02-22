package com.jigpud.snow.interceptor;

import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.constant.HeaderConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : jigpud
 * 验证token
 */
@Slf4j
@Component
public class TokenCheckInterceptor implements HandlerInterceptor {
    private final TokenService tokenService;

    @Autowired
    TokenCheckInterceptor(TokenService userService) {
        this.tokenService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(HeaderConstant.AUTHORIZATION);
        if (token != null) {
            return tokenService.isLogin(token);
        }
        return false;
    }
}
