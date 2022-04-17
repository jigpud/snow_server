package com.jigpud.snow.repository.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Comment;

/**
 * @author : jigpud
 */
public interface CommentRepository {
    void addComment(Comment comment);

    Comment getComment(String commentId);

    Page<Comment> getUserCommentList(String userid, long pageSize, long currentPage);

    Page<Comment> getStoryCommentList(String storyId, long pageSize, long currentPage);

    Page<Comment> getCommentReplyList(String commentId, long pageSize, long currentPage);
}
