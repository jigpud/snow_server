package com.jigpud.snow.service.token;

import io.jsonwebtoken.Claims;

import java.util.Date;

/**
 * @author : jigpud
 * 提供token相关的服务
 */
public interface TokenService {
    /**
     * 根据用户Id和用户名创建token
     * @param userid userid
     * @return token
     */
    String createToken(String userid);

    /**
     * 解析token
     * @param token token
     * @return 自定义声明的参数
     */
    Claims parseToken(String token);

    /**
     * 根据token获取用户Id
     * @param token token
     * @return userid
     */
    String getUserid(String token);

    /**
     * 根据token获取过期时间
     * @param token token
     * @return expiration
     */
    Date getExpiration(String token);

    /**
     * 判断token是否过期
     * @param token token
     * @return 是否过期
     */
    boolean isExpiration(String token);

    /**
     * 验证token是否可用
     * @param token token
     * @return 是否可用
     */
    boolean verify(String token);

    /**
     * 标记为已登录
     * @param token token
     */
    void markLogin(String token);

    /**
     * 标记为未登录
     * @param token token
     */
    void markLogout(String token);

    /**
     * 标记为admin
     * @param token token
     */
    void markAdmin(String token);

    /**
     * 标记为非admin
     * @param token token
     */
    void markUser(String token);

    /**
     * 是否已登录
     * @param token token
     * @return 是否已登录
     */
    boolean isLogin(String token);

    /**
     * 是否是admin
     * @param token token
     * @return 是否是admin
     */
    boolean isAdmin(String token);
}
