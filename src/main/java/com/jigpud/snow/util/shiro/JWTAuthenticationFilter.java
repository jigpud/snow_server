package com.jigpud.snow.util.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        CorsSupport.support(request, response);
        if (HttpMethod.OPTIONS.matches(((HttpServletRequest) request).getMethod())) {
            return false;
        }
        return super.preHandle(request, response);
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
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        ResponseBody<?> authFailed = Response.response(401, "认证失败！");
        try {
            response.getWriter().print(objectMapper.writeValueAsString(authFailed));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return false;
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
            return false;
        }
    }
}
