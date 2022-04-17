package com.jigpud.snow.util.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.util.response.Response;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        CorsSupport.support(request, response);
        if (HttpMethod.OPTIONS.matches(((HttpServletRequest) request).getMethod())) {
            return false;
        }
        Subject subject = getSubject(request, response);
        subject.logout();
        ResponseBody<?> logoutSuccess = Response.responseSuccess();
        response.getWriter().print(objectMapper.writeValueAsString(logoutSuccess));
        return super.preHandle(request, response);
    }
}
