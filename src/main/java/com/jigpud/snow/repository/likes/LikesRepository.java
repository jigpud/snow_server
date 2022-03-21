package com.jigpud.snow.repository.likes;

/**
 * @author jigpud
 */
public interface LikesRepository {
    /**
     * 增加一个点赞关系
     * @param storyId 游记
     * @param userid 用户
     */
    void add(String storyId, String userid);

    /**
     * 移除一个点赞关系
     * @param storyId 游记
     * @param userid 用户
     */
    void remove(String storyId, String userid);

    /**
     * 是否有这样一个点赞关系
     * @param storyId 游记
     * @param userid 用户
     * @return 是否有这样一个点赞关系
     */
    boolean have(String storyId, String userid);

    /**
     * 获取用户获赞总数
     * @param userid 用户
     * @return 获赞总数
     */
    long userLikes(String userid);

    /**
     * 获取游记获赞总数
     * @param storyId 游记
     * @return 获赞总数
     */
    long storyLikes(String storyId);
}
