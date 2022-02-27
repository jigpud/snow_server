package com.jigpud.snow.util.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigpud.snow.util.response.Response;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author : jigpud
 * 自定义shiro的退出登录拦截
 */
public class JWTLogoutFilter extends LogoutFilter {
    private final ObjectMapper objectMapper;

    public JWTLogoutFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        subject.logout();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().print(objectMapper.writeValueAsString(Response.responseSuccess()));
        return false;
    }
}
