package com.jigpud.snow.interceptor;

import com.jigpud.snow.service.admin.AdminService;
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
    private final AdminService adminService;

    @Autowired
    AdminCheckInterceptor(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(HeaderConstant.AUTHORIZATION);
        if (token != null) {
            return adminService.isAdmin(token);
        }
        return false;
    }
}
