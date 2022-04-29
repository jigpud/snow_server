package com.jigpud.snow.service.like;

/**
 * @author : jigpud
 */
public interface LikeService {
    /**
     * 获取游记的点赞数
     * @param storyId 游记
     * @return 点赞数
     */
    long storyLikes(String storyId);

    /**
     * 点赞游记
     * @param userid 用户
     * @param storyId 游记
     */
    void likeStory(String storyId, String userid);

    /**
     * 取消点赞游记
     * @param userid 用户
     * @param storyId 游记
     */
    void unlikeStory(String storyId, String userid);

    /**
     * 用户是否点赞游记
     * @param userid 用户
     * @param storyId 游记
     * @return 是否点赞
     */
    boolean haveLikedStory(String storyId, String userid);

    /**
     * 获取评论的点赞数
     * @param commentId 评论
     * @return 点赞数
     */
    long commentLikes(String commentId);

    /**
     * 点赞评论
     * @param userid 用户
     * @param commentId 评论
     */
    void likeComment(String commentId, String userid);

    /**
     * 取消点赞评论
     * @param userid 用户
     * @param commentId 评论
     */
    void unlikeComment(String commentId, String userid);

    /**
     * 用户是都点赞了评论
     * @param userid 用户
     * @param commentId 评论
     */
    boolean haveLikedComment(String commentId, String userid);

    /**
     * 获取用户获赞数
     * @param userid 用户
     * @return 获赞数
     */
    long likes(String userid);
}
