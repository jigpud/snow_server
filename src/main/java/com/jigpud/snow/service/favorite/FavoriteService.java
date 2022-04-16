package com.jigpud.snow.service.favorite;

/**
 * @author : jigpud
 */
public interface FavoriteService {
    /**
     * 获取用户收藏总数
     * @param userid 用户
     * @return 收藏总数
     */
    long favorites(String userid);

    /**
     * 收藏游记
     * @param userid 用户
     * @param storyId 游记
     */
    void favorite(String userid, String storyId);

    /**
     * 取消收藏游记
     * @param userid 用户
     * @param storyId 游记
     */
    void unFavorite(String userid, String storyId);

    /**
     * 获取用户是否已经收藏了游记
     * @param userid 用户
     * @param storyId 游记
     * @return 是否已经收藏了
     */
    boolean haveFavorite(String userid, String storyId);
}
