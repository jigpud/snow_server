package com.jigpud.snow.util.constant;

/**
 * @author : jigpud
 */
public class PathConstant {
    /*
    通用接口
     */

    // 获取短信验证码
    public static final String VERIFICATION_CODE = "/verificationCode";

    // 注册
    public static final String REGISTER = "/register";

    // token刷新
    public static final String REFRESH_TOKEN = "/refreshToken";

    // 账号密码登录
    public static final String LOGIN = "/login";

    // 短信验证码登录
    public static final String LOGIN_WITH_VERIFICATION_CODE = "/loginWithVC";

    // 退出登录
    public static final String LOGOUT = "/logout";

    /*
    普通用户相关接口
     */

    // 找回密码
    public static final String RETRIEVE_PASSWORD = "/retrievePassword";

    // 更新个人信息
    public static final String UPDATE_INFORMATION = "/updateInfo";

    // 获取个人信息
    public static final String GET_USER_INFORMATION = "/userInfo";

    // 获取用户的所有游记
    public static final String GET_STORY_LIST = "/stories";

    // 发布游记
    public static final String RELEASE_STORY = "/story/release";

    // 获取用户动态的所有游记
    public static final String GET_MOMENTS_STORY_LIST = "/story/moments";

    // 点赞游记
    public static final String LIKE_STORY = "/story/like";

    // 取消游记点赞
    public static final String UNLIKE_STORY = "/story/unlike";

    // 关注
    public static final String FOLLOW = "/user/follow";

    // 取消关注
    public static final String UNFOLLOW = "/user/unfollow";

    /*
    admin相关接口
     */

    // admin登录
    public static final String ADMIN_LOGIN = "/adminLogin";

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
}
