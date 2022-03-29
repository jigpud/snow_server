package com.jigpud.snow.repository.commentlikes;

/**
 * @author : jigpud
 */
public interface CommentLikesRepository {
    /**
     * 点赞一条评论
     * @param commentId 评论
     * @param userid 用户
     */
    void add(String commentId, String userid);

    /**
     * 取消点赞一条评论
     * @param commentId 评论
     * @param userid 用户
     */
    void remove(String commentId, String userid);

    /**
     * 用户是否点赞了某条评论
     * @param commentId 评论
     * @param userid 用户
     * @return 是否已点赞
     */
    boolean have(String commentId, String userid);

    /**
     * 获取评论的点赞数
     * @param commentId 评论
     * @return 评论的点赞数
     */
    long commentLikes(String commentId);
}
