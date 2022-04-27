package com.jigpud.snow.service.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Comment;
import com.jigpud.snow.repository.comment.CommentRepository;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.util.encrypt.Encryptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment getComment(String commentId) {
        return commentRepository.getComment(commentId);
    }

    @Override
    public PageData<Comment> storyCommentList(String storyId, long pageSize, long currentPage) {
        return PageData.fromPage(commentRepository.getStoryCommentList(storyId, pageSize, currentPage));
    }

    @Override
    public PageData<Comment> commentReplyList(String commentId, long pageSize, long currentPage) {
        return PageData.fromPage(commentRepository.getCommentReplyList(commentId, pageSize, currentPage));
    }

    @Override
    public PageData<Comment> userCommentList(String userid, long pageSize, long currentPage) {
        return PageData.fromPage(commentRepository.getUserCommentList(userid, pageSize, currentPage));
    }

    @Override
    public String comment(String storyId, String userid, String content) {
        String commentId = Encryptor.uuid();
        Comment comment = new Comment();
        comment.setStoryId(storyId);
        comment.setAuthorId(userid);
        comment.setContent(content);
        comment.setCommentId(commentId);
        comment.setPid("");
        comment.setCommentTime(System.currentTimeMillis());
        commentRepository.addComment(comment);
        return commentId;
    }

    @Override
    public String reply(String replyTo, String userid, String content) {
        Comment comment = commentRepository.getComment(replyTo);
        String replyCommentId = Encryptor.uuid();
        Comment reply = new Comment();
        reply.setStoryId(comment.getStoryId());
        reply.setAuthorId(userid);
        reply.setContent(content);
        reply.setCommentId(replyCommentId);
        reply.setPid(replyTo);
        comment.setCommentTime(System.currentTimeMillis());
        commentRepository.addComment(comment);
        return replyCommentId;
    }
}
