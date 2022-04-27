package com.jigpud.snow.service.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Comment;
import com.jigpud.snow.response.PageData;

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
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 游记的评论列表分页
     */
    PageData<Comment> storyCommentList(String storyId, long pageSize, long currentPage);

    /**
     * 获取评论的回复列表
     * @param commentId 评论
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 评论的回复列表分页
     */
    PageData<Comment> commentReplyList(String commentId, long pageSize, long currentPage);

    /**
     * 获取用户评论列表
     * @param userid 用户
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 用户评论列表分页
     */
    PageData<Comment> userCommentList(String userid, long pageSize, long currentPage);

    /**
     * 评论游记
     * @param storyId 游记
     * @param userid 用户
     * @param content 评论内容
     * @return 评论id
     */
    String comment(String storyId, String userid, String content);

    /**
     * 回复别人的评论
     * @param replyTo 要恢复的评论
     * @param userid 用户
     * @param content 回复的内容
     * @return 评论id
     */
    String reply(String replyTo, String userid, String content);
}
