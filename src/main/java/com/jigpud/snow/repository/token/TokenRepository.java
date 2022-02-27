package com.jigpud.snow.repository.token;

/**
 * @author : jigpud
 */
public interface TokenRepository {
    /**
     * 保存当前有效的refreshToken
     * @param userid userid
     * @param refreshToken refreshToken
     */
    void saveRefreshToken(String userid, String refreshToken);

    /**
     * 获取当前有效的refreshToken
     * @param userid userid
     * @return refreshToken
     */
    String getRefreshToken(String userid);

    /**
     * 删除refreshToken
     * @param userid userid
     */
    void removeRefreshToken(String userid);
}
