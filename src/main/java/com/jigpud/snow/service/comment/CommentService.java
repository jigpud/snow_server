package com.jigpud.snow.service.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Comment;

/**
 * @author : jigpud
 * 评论
 */
public interface CommentService {
    /**
     * 获取评论详情
     * @param commentId 评论
     * @return 评论详情
     */
    Comment getComment(String commentId);

    /**
     * 获取游记的评论列表
     * @param storyId 游记
     * @param pageCount 分页大小
     * @param page 页码
     * @return 游记的评论列表分页
     */
    Page<Comment> storyCommentList(String storyId, long pageCount, long page);

    /**
     * 获取评论的回复列表
     * @param commentId 评论
     * @param pageCount 分页大小
     * @param page 页码
     * @return 评论的回复列表分页
     */
    Page<Comment> commentReplyList(String commentId, long pageCount, long page);

    /**
     * 获取用户评论列表
     * @param userid 用户
     * @param pageCount 分页大小
     * @param page 页码
     * @return 用户评论列表分页
     */
    Page<Comment> userCommentList(String userid, long pageCount, long page);

    /**
     * 评论游记
     * @param storyId 游记
     * @param userid 用户
     * @param content 评论内容
     */
    void comment(String storyId, String userid, String content);

    /**
     * 回复别人的评论
     * @param replyTo 要恢复的评论
     * @param userid 用户
     * @param content 回复的内容
     */
    void reply(String replyTo, String userid, String content);

    /**
     * 获取评论的点赞数
     * @param commentId 评论
     * @return 点赞数
     */
    long likes(String commentId);

    /**
     * 点赞一条评论
     * @param commentId 评论
     * @param userid 用户
     */
    void like(String commentId, String userid);

    /**
     * 取消点赞一条评论
     * @param commentId 评论
     * @param userid 用户
     */
    void unlike(String commentId, String userid);

    /**
     * 用户是否点赞某条评论
     * @param commentId 评论
     * @param userid 用户
     * @return 是否点赞
     */
    boolean haveLiked(String commentId, String userid);
}
