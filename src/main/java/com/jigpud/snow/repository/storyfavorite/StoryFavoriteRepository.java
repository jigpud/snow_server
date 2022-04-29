package com.jigpud.snow.repository.storyfavorite;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.StoryFavorite;

/**
 * @author : jigpud
 */
public interface StoryFavoriteRepository {
    /**
     * 增加一条收藏
     * @param userid 用户
     * @param storyId 游记
     */
    void add(String storyId, String userid);

    /**
     * 删除一条收藏
     * @param userid 用户
     * @param storyId 游记
     */
    void remove(String storyId, String userid);

    /**
     * 是否有这样一条收藏
     * @param userid 用户
     * @param storyId 游记
     * @return 是否有这样的收藏
     */
    boolean have(String storyId, String userid);

    /**
     * 获取用户的收藏列表
     * @param userid 用户
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 收藏列表分页
     */
    Page<StoryFavorite> favoriteList(String userid, long pageSize, long currentPage);

    /**
     * 获取用户的收藏数量
     * @param userid 用户
     * @return 收藏数量
     */
    long favorites(String userid);
}
