package com.jigpud.snow.interceptor;

import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.HeaderConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : jigpud
 */
@Component
public class AdminCheckInterceptor implements HandlerInterceptor {
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    AdminCheckInterceptor(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(HeaderConstant.AUTHORIZATION);
        if (tokenService.verify(token)) {
            return userService.isAdmin(token);
        }
        return false;
    }
}
