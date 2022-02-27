package com.jigpud.snow.util.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author : jigpud
 * 自定义shiro的登陆拦截
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicHttpAuthenticationFilter {
    private final ObjectMapper objectMapper;

    public JWTAuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return true;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        log.debug("createToken");
        String token = getAuthzHeader(request);
        return new JWTToken(token);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        log.debug("executeLogin");
        try {
            AuthenticationToken authenticationToken = createToken(request, response);
            Subject subject = getSubject(request, response);
            subject.login(authenticationToken);
            return true;
        } catch (Exception e) {
            ResponseBody<?> authFailed = Response.response(401, "认证失败！");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(objectMapper.writeValueAsString(authFailed));
            return false;
        }
    }
}
