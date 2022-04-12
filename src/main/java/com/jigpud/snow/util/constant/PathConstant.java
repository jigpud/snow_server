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

    // token刷新
    public static final String REFRESH_TOKEN = "/refreshToken";

    /*
    普通用户相关接口
     */

    // 注册
    public static final String REGISTER = "/user/register";

    // 账号密码登录
    public static final String LOGIN = "/user/login";

    // 短信验证码登录
    public static final String LOGIN_WITH_VERIFICATION_CODE = "/user/loginWithVC";

    // 退出登录
    public static final String LOGOUT = "/user/logout";

    // 找回密码
    public static final String RETRIEVE_PASSWORD = "/user/retrievePassword";

    // 更新个人信息
    public static final String UPDATE_INFORMATION = "/user/updateInfo";

    // 获取个人信息
    public static final String GET_USER_INFORMATION = "/user/info";

    // 获取用户自己的所有游记
    public static final String GET_SELF_STORY_LIST = "/story/self";

    // 获取用户的游记
    public static final String GET_USER_STORY_LIST = "/story/list";

    // 发布游记
    public static final String RELEASE_STORY = "/story/release";

    // 获取用户自己动态的所有游记
    public static final String GET_MOMENTS_STORY_LIST = "/story/moments/self";

    // 根据关键词模糊查询游记
    public static final String SEARCH_STORY = "/search/story";

    // 根据关键词模糊查询用户
    public static final String SEARCH_USER = "/search/user";

    // 根据关键词模糊查询景点
    public static final String SEARCH_ATTRACTION = "/search/attraction";

    // 获取游记详情
    public static final String GET_STORY = "/story";

    // 点赞游记
    public static final String LIKE_STORY = "/story/like";

    // 取消游记点赞
    public static final String UNLIKE_STORY = "/story/unlike";

    // 评论
    public static final String COMMENT_STORY = "/story/comment";

    // 游记的评论列表
    public static final String STORY_COMMENT_LIST = "/story/comment/list";

    // 点赞评论
    public static final String LIKE_COMMENT = "/story/comment/like";

    // 取消评论点赞
    public static final String UNLIKE_COMMENT = "/story/comment/unlike";

    // 回复评论
    public static final String REPLY_COMMENT = "/story/comment/reply";

    // 评论的回复列表
    public static final String COMMENT_REPLY_LIST = "/story/comment/reply/list";

    // 关注
    public static final String FOLLOW = "/user/follow";

    // 取消关注
    public static final String UNFOLLOW = "/user/unfollow";

    /*
    admin用户相关接口
     */

    // admin登录
    public static final String ADMIN_LOGIN = "/admin/login";

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
