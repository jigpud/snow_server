package com.jigpud.snow.repository.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Comment;

/**
 * @author : jigpud
 */
public interface CommentRepository {
    void addComment(Comment comment);

    Comment getComment(String commentId);

    Page<Comment> getUserCommentList(String userid, long pageCount, long page);

    Page<Comment> getStoryCommentList(String storyId, long pageCount, long page);

    Page<Comment> getCommentReplyList(String commentId, long pageCount, long page);
}
