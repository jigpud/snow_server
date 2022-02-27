package com.jigpud.snow.service.token;

import io.jsonwebtoken.Claims;

import java.util.Date;

/**
 * @author : jigpud
 * 提供token相关的服务
 */
public interface TokenService {
    /**
     * 根据refreshToken和用户名创建token
     * @param refreshToken refreshToken
     * @return token
     */
    String createToken(String refreshToken);

    /**
     * 根据token创建refreshToken
     * @param userid userid
     * @return refreshToken
     */
    String createRefreshToken(String userid);

    /**
     * 验证refreshToken是否有效
     * @param refreshToken refreshToken
     * @return 是否有效
     */
    boolean verifyRefreshToken(String refreshToken);

    /**
     * 刷新token
     * @param refreshToken refreshToken
     * @return 新token
     */
    String refresh(String refreshToken);

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
     * 验证token是否可用，仅验证token格式是否正确与是否过期
     * @param token token
     * @return 是否可用
     */
    boolean verify(String token);

    /**
     * 销毁refreshToken
     * @param userid userid
     */
    void expireRefreshToken(String userid);
}
