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
    public static final String POST_STORY = "/story/post";

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

    // 收藏游记
    public static final String FAVORITE_STORY = "/favorite/story/favorite";

    // 取消游记收藏
    public static final String UN_FAVORITE_STORY = "/favorite/story/unFavorite";

    // 获取用户自己的游记收藏列表
    public static final String GET_SELF_FAVORITE_STORY_LIST = "/favorite/story/self";

    // 获取用户的游记收藏列表
    public static final String GET_USER_FAVORITE_STORY_LIST = "/favorite/story/list";

    // 评论
    public static final String COMMENT_STORY = "/story/comment/post";

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

    // 获取评论详情
    public static final String GET_COMMENT = "/story/comment";

    // 关注
    public static final String FOLLOW = "/user/follow";

    // 取消关注
    public static final String UNFOLLOW = "/user/unfollow";

    // 获取景点详情
    public static final String GET_ATTRACTION = "/attraction";

    // 获取景点下的游记列表
    public static final String GET_ATTRACTION_STORY_LIST = "/attraction/story/list";

    // 为景点打分
    public static final String SCORE_ATTRACTION = "/attraction/score";

    // 获取景点的照片列表
    public static final String GET_ATTRACTION_PHOTO_LIST = "/attraction/photo";

    // 上传景点照片
    public static final String UPLOAD_ATTRACTION_PHOTO = "/attraction/photo/upload";

    // 删除景点照片
    public static final String DELETE_ATTRACTION_PHOTO = "/attraction/photo/delete";

    // 关注景点
    public static final String FOLLOW_ATTRACTION = "attraction/follow";

    // 取消关注景点
    public static final String UNFOLLOW_ATTRACTION = "attraction/unfollow";

    // 获取头像的upload token
    public static final String GET_AVATAR_UPLOAD_TOKEN = "/upload/token/avatar";

    // 获取景点照片的upload token
    public static final String GET_ATTRACTION_IMG_UPLOAD_TOKEN = "/upload/token/attraction";

    // 获取游记照片的upload token
    public static final String GET_STORY_IMG_UPLOAD_TOKEN = "/upload/token/story";

    // 获取个人信息的背景图片
    public static final String GET_USER_PROFILE_BACKGROUND_IMG_UPLOAD_TOKEN = "/upload/token/userProfileBackground";

    // 获取热门景点列表
    public static final String GET_HOT_ATTRACTION_LIST = "/recommend/attraction/hot";

    // 获取推荐景点列表
    public static final String GET_RECOMMEND_ATTRACTION_LIST = "/recommend/attraction";

    // 获取推荐用户列表
    public static final String GET_RECOMMEND_USER_LIST = "/recommend/user";

    /*
    admin用户相关接口
     */

    // admin登录
    public static final String ADMIN_LOGIN = "/admin/login";

    // 更新用户信息
    public static final String UPDATE_USER = "/admin/user/update";

    // 删除用户
    public static final String DELETE_USER = "/admin/user/delete";

    // 创建用户
    public static final String CREATE_USER = "/admin/user/create";

    // 查询用户
    public static final String QUERY_USER = "/admin/user/query";

    // 添加景点
    public static final String ADD_ATTRACTION = "/admin/attraction/create";

    // 删除景点
    public static final String DELETE_ATTRACTION = "/admin/attraction/delete";

    // 修改景点
    public static final String UPDATE_ATTRACTION = "/admin/attraction/update";

    // 查询景点
    public static final String QUERY_ATTRACTION = "/admin/attraction/query";

    // 删除景点照片
    public static final String ADMIN_DELETE_ATTRACTION_PHOTO = "/admin/attraction/photo/delete";
}
