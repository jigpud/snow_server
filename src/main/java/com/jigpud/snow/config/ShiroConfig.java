package com.jigpud.snow.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jigpud.snow.service.permission.PermissionService;
import com.jigpud.snow.service.role.RoleService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.shiro.JWTAuthenticationFilter;
import com.jigpud.snow.util.shiro.JWTLogoutFilter;
import com.jigpud.snow.util.shiro.JWTRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : jigpud
 */
@Configuration
public class ShiroConfig {
    @Bean
    JWTRealm jwtRealm(RoleService roleService, PermissionService permissionService, TokenService tokenService) {
        return new JWTRealm(roleService, permissionService, tokenService);
    }

    @Bean
    DefaultWebSecurityManager securityManager(JWTRealm jwtRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 设置realm
        securityManager.setRealm(jwtRealm);

        // 禁止session持久化
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager, ObjectMapper objectMapper) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置jwtFilter
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTAuthenticationFilter(objectMapper));
        filterMap.put("jwtLogout", new JWTLogoutFilter(objectMapper));
        shiroFilterFactoryBean.setFilters(filterMap);

        // 设置path和filter的映射
        Map<String , String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/**", "jwt");
        filterChainDefinitionMap.put(PathConstant.LOGIN, "anon");
        filterChainDefinitionMap.put(PathConstant.VERIFICATION_CODE, "anon");
        filterChainDefinitionMap.put(PathConstant.REGISTER, "anon");
        filterChainDefinitionMap.put(PathConstant.REFRESH_TOKEN, "anon");
        filterChainDefinitionMap.put(PathConstant.RETRIEVE_PASSWORD, "anon");
        filterChainDefinitionMap.put(PathConstant.LOGIN_WITH_VERIFICATION_CODE, "anon");
        filterChainDefinitionMap.put(PathConstant.LOGOUT, "jwt,jwtLogout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }
}
