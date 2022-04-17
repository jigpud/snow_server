package com.jigpud.snow.repository.userfollow;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.UserFollow;

/**
 * @author : jigpud
 */
public interface UserFollowRepository {
    /**
     * 增加一个关注关系
     * @param follower 关注者
     * @param userid 被关注者
     */
    void add(String follower, String userid);

    /**
     * 移除一个关注关系
     * @param follower 关注者
     * @param userid 被关注者
     */
    void remove(String follower, String userid);

    /**
     * 是否有一个这样的关注关系
     * @param follower 关注者
     * @param userid 被关注者
     * @return 是否有这样一个关系
     */
    boolean have(String follower, String userid);

    /**
     * 获取关注者列表
     * @param userid 自己
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 关注者列表分页
     */
    Page<UserFollow> followerList(String userid, long pageSize, long currentPage);

    /**
     * 获取关注者数量
     * @param userid 自己
     * @return 关注者数量
     */
    long followerCount(String userid);

    /**
     * 获取关注列表
     * @param userid 自己
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 关注列表分页
     */
    Page<UserFollow> followingList(String userid, long pageSize, long currentPage);

    /**
     * 获取关注数量
     * @param userid 自己
     * @return 关注数量
     */
    long followingCount(String userid);
}
