package com.jigpud.snow.repository.favorite;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Favorite;

/**
 * @author : jigpud
 */
public interface FavoriteRepository {
    /**
     * 增加一条收藏
     * @param userid 用户
     * @param storyId 游记
     */
    void add(String userid, String storyId);

    /**
     * 删除一条收藏
     * @param userid 用户
     * @param storyId 游记
     */
    void remove(String userid, String storyId);

    /**
     * 是否有这样一条收藏
     * @param userid 用户
     * @param storyId 游记
     * @return 是否有这样的收藏
     */
    boolean have(String userid, String storyId);

    /**
     * 获取用户的收藏列表
     * @param userid 用户
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 收藏列表分页
     */
    Page<Favorite> favoriteList(String userid, long pageSize, long currentPage);

    /**
     * 获取用户的收藏数量
     * @param userid 用户
     * @return 收藏数量
     */
    long favorites(String userid);
}
