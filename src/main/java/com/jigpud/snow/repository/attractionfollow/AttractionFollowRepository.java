package com.jigpud.snow.repository.attractionfollow;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.AttractionFollow;

/**
 * @author : jigpud
 */
public interface AttractionFollowRepository {
    /**
     * 增加一个关注关系
     * @param follower 关注者
     * @param attractionId 景点
     */
    void add(String follower, String attractionId);

    /**
     * 移除一个关注关系
     * @param follower 关注者
     * @param attractionId 景点
     */
    void remove(String follower, String attractionId);

    /**
     * 是否有一个这样的关注关系
     * @param follower 关注者
     * @param attractionId 景点
     * @return 是否有这样一个关系
     */
    boolean have(String follower, String attractionId);

    /**
     * 获取关注者列表
     * @param attractionId 景点
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 关注者列表分页
     */
    Page<AttractionFollow> followerList(String attractionId, long pageSize, long currentPage);

    /**
     * 获取关注者数量
     * @param attractionId 景点
     * @return 关注者数量
     */
    long followerCount(String attractionId);

    /**
     * 获取关注列表
     * @param userid 自己
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 关注列表分页
     */
    Page<AttractionFollow> followingList(String userid, long pageSize, long currentPage);

    /**
     * 获取关注数量
     * @param userid 自己
     * @return 关注数量
     */
    long followingCount(String userid);
}