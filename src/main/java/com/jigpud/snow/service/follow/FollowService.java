package com.jigpud.snow.service.follow;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.User;
import com.jigpud.snow.response.PageData;

/**
 * @author : jigpud
 */
public interface FollowService {
    /**
     * 关注一个用户
     * @param follower 自己
     * @param userid 被关注的人
     */
    void followUser(String follower, String userid);

    /**
     * 取消关注一个用户
     * @param follower 自己
     * @param userid 被关注的人
     */
    void unfollowUser(String follower, String userid);

    /**
     * 关注一个景点
     * @param follower 用户
     * @param attractionId 景点
     */
    void followAttraction(String attractionId, String follower);

    /**
     * 取消关注一个景点
     * @param follower 用户
     * @param attractionId 景点
     */
    void unfollowAttraction(String attractionId, String follower);

    /**
     * 获取用户的粉丝总数
     * @param userid 用户
     * @return 粉丝总数
     */
    long getUserFollowers(String userid);

    /**
     * 获取用户的粉丝列表
     * @param userid 用户
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 粉丝列表分页
     */
    PageData<User> getUserFollowerList(String userid, long pageSize, long currentPage);

    /**
     * 获取用户关注的用户数
     * @param userid 用户
     * @return 关注数
     */
    long getFollowingUserCount(String userid);

    /**
     * 获取用户关注的用户列表
     * @param userid 用户
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 关注列表分页
     */
    PageData<User> getFollowingUserList(String userid, long pageSize, long currentPage);

    /**
     * 获取用户关注的景点数
     * @param userid 用户
     * @return 关注数
     */
    long getFollowingAttractionCount(String userid);

    /**
     * 获取景点的关注数量
     * @param attractionId 景点
     * @return 景点的关注数
     */
    long getAttractionFollowers(String attractionId);

    /**
     * 获取用戶关注的景点列表
     * @param userid 用户
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 关注列表分页
     */
    PageData<Attraction> getFollowingAttractionList(String userid, long pageSize, long currentPage);

    /**
     * 是否关注了该用户
     * @param follower 自己
     * @param userid 关注
     * @return 是否关注了
     */
    boolean haveFollowedUser(String follower, String userid);

    /**
     * 是否关注了该景点
     * @param follower 自己
     * @param attractionId 景点
     * @return 是否关注了
     */
    boolean haveFollowedAttraction(String attractionId, String follower);

    /**
     * 获取用户的关注数量
     * @param userid 用户
     * @return 关注数量
     */
    long getFollowingCount(String userid);
}
