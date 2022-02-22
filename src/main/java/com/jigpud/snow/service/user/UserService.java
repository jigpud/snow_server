package com.jigpud.snow.service.user;

/**
 * @author : jigpud
 * 用户服务
 */
public interface UserService {
    /**
     * 注册
     * @param username 电话号码
     * @param password 密码
     */
    void register(String username, String password);

    /**
     * 登录
     * @param username 电话号码
     * @param password 密码
     * @return token
     */
    String login(String username, String password);

    /**
     * admin登录
     * @param username 电话号码
     * @param password 密码
     * @return token
     */
    String adminLogin(String username, String password);

    /**
     * 退出登录
     * @param token token
     */
    void logout(String token);

    /**
     * 退出admin登录
     * @param token token
     */
    void adminLogout(String token);

    /**
     * 登录是否有效
     * @param token token
     * @return 是否有效
     */
    boolean isLogin(String token);

    /**
     * 是否是admin
     * @param token token
     * @return 是否是admin
     */
    boolean isAdmin(String token);
}
