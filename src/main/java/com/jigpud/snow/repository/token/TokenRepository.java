package com.jigpud.snow.repository.token;

/**
 * @author : jigpud
 */
public interface TokenRepository {
    /**
     * 标记为登录状态
     * @param token token
     * @param userid userid
     * @param expiration expiration, unit: seconds
     */
    void markLogin(String token, String userid, long expiration);

    /**
     * 标记为未登录状态
     * @param token token
     * @param userid userid
     */
    void markLogout(String token, String userid);

    /**
     * 标记为admin用户
     * @param token token
     * @param userid userid
     * @param expiration expiration, unit: seconds
     */
    void markAdmin(String token, String userid, long expiration);

    /**
     * 标记为普通用户
     * @param token token
     * @param userid userid
     */
    void markUser(String token, String userid);

    /**
     * 是否已登录
     * @param token token
     * @param userid userid
     * @return 是否已登录
     */
    boolean isLogin(String token, String userid);

    /**
     * 是否是admin
     * @param token token
     * @param userid userid
     * @return 是否是admin
     */
    boolean isAdmin(String token, String userid);
}
