package com.jigpud.snow.util.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : jigpud
 */
public class CorsSupport {
    public static void support(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json");
        httpResponse.addHeader("Access-control-Allow-Origin", httpRequest.getHeader("Origin"));
        httpResponse.addHeader("Access-Control-Allow-Methods", httpRequest.getHeader("Access-Control-Request-Method"));
        httpResponse.addHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
    }
}
