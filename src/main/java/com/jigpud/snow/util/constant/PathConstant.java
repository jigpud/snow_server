package com.jigpud.snow.util.constant;

/**
 * @author : jigpud
 */
public class PathConstant {
    // 账号密码登陆
    public static final String LOGIN = "/login";

    // 短信验证码登陆
    public static final String LOGIN_WITH_VERIFICATION_CODE = "/loginWithVC";

    // 退出登陆
    public static final String LOGOUT = "/logout";

    // 获取用户列表
    public static final String GET_USER_LIST = "/users";

    // 更新用户信息
    public static final String UPDATE_USER = "/user/update";

    // 删除用户
    public static final String DELETE_USER = "/user/delete";

    // 创建用户
    public static final String CREATE_USER = "/user/create";

    // 查询用户
    public static final String QUERY_USER = "/user/query";

    // 获取短信验证码
    public static final String VERIFICATION_CODE = "/verificationCode";

    // 注册
    public static final String REGISTER = "/register";

    // token刷新
    public static final String REFRESH_TOKEN = "/refreshToken";

    // 找回密码
    public static final String RETRIEVE_PASSWORD = "/retrievePassword";
}
