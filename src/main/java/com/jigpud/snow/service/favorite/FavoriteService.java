package com.jigpud.snow.service.favorite;

import com.jigpud.snow.response.PageData;

/**
 * @author : jigpud
 */
public interface FavoriteService {
    /**
     * 获取用户收藏总数
     * @param userid 用户
     * @return 收藏总数
     */
    long storyFavorites(String userid);

    /**
     * 收藏游记
     * @param userid 用户
     * @param storyId 游记
     */
    void favoriteStory(String storyId, String userid);

    /**
     * 取消收藏游记
     * @param userid 用户
     * @param storyId 游记
     */
    void unFavoriteStory(String storyId, String userid);

    /**
     * 获取用户是否已经收藏了游记
     * @param userid 用户
     * @param storyId 游记
     * @return 是否已经收藏了
     */
    boolean haveFavoriteStory(String storyId, String userid);

    /**
     * 获取用户的收藏列表
     * @param userid 用户
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 收藏列表分页
     */
    PageData<String> storyFavoriteList(String userid, long pageSize, long currentPage);
}
