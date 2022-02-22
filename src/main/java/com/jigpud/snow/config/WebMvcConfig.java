package com.jigpud.snow.config;

import com.jigpud.snow.interceptor.AdminCheckInterceptor;
import com.jigpud.snow.interceptor.TokenCheckInterceptor;
import com.jigpud.snow.util.constant.PathConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : jigpud
 * 添加拦截器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final TokenCheckInterceptor tokenCheckInterceptor;
    private final AdminCheckInterceptor adminCheckInterceptor;

    @Autowired
    WebMvcConfig(TokenCheckInterceptor tokenCheckInterceptor, AdminCheckInterceptor adminCheckInterceptor) {
        this.tokenCheckInterceptor = tokenCheckInterceptor;
        this.adminCheckInterceptor = adminCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // token验证
        registry.addInterceptor(tokenCheckInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(PathConstant.USER_LOGIN)
                .excludePathPatterns(PathConstant.USER_LOGOUT)
                .excludePathPatterns(PathConstant.ADMIN_LOGIN)
                .excludePathPatterns(PathConstant.ADMIN_LOGOUT);

        // admin验证
        registry.addInterceptor(adminCheckInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(PathConstant.ADMIN_LOGIN)
                .excludePathPatterns(PathConstant.ADMIN_LOGOUT);
    }
}
