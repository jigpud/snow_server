package com.jigpud.snow.service.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Comment;
import com.jigpud.snow.repository.comment.CommentRepository;
import com.jigpud.snow.repository.commentlikes.CommentLikesRepository;
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
    private final CommentLikesRepository commentLikesRepository;

    @Autowired
    CommentServiceImpl(CommentRepository commentRepository, CommentLikesRepository commentLikesRepository) {
        this.commentRepository = commentRepository;
        this.commentLikesRepository = commentLikesRepository;
    }

    @Override
    public Comment getComment(String commentId) {
        return commentRepository.getComment(commentId);
    }

    @Override
    public Page<Comment> storyCommentList(String storyId, long pageCount, long page) {
        return commentRepository.getStoryCommentList(storyId, pageCount, page);
    }

    @Override
    public Page<Comment> commentReplyList(String commentId, long pageCount, long page) {
        return commentRepository.getCommentReplyList(commentId, pageCount, page);
    }

    @Override
    public Page<Comment> userCommentList(String userid, long pageCount, long page) {
        return commentRepository.getUserCommentList(userid, pageCount, page);
    }

    @Override
    public void comment(String storyId, String userid, String content) {
        Comment comment = new Comment();
        comment.setStoryId(storyId);
        comment.setAuthorId(userid);
        comment.setContent(content);
        comment.setCommentId(Encryptor.uuid());
        comment.setPid("");
        commentRepository.addComment(comment);
    }

    @Override
    public void reply(String replyTo, String userid, String content) {
        Comment comment = commentRepository.getComment(replyTo);
        if (comment != null) {
            Comment reply = new Comment();
            reply.setStoryId(comment.getStoryId());
            reply.setAuthorId(userid);
            reply.setContent(content);
            reply.setCommentId(Encryptor.uuid());
            reply.setPid(replyTo);
            commentRepository.addComment(comment);
        } else {
            log.debug("reply: {} not exists!", replyTo);
        }
    }

    @Override
    public long likes(String commentId) {
        return commentLikesRepository.commentLikes(commentId);
    }

    @Override
    public void like(String commentId, String userid) {
        if (!commentLikesRepository.have(commentId, userid)) {
            commentLikesRepository.add(commentId, userid);
        } else {
            log.debug("like: {} already liked {}!", userid, commentId);
        }
    }

    @Override
    public void unlike(String commentId, String userid) {
        if (commentLikesRepository.have(commentId, userid)) {
            commentLikesRepository.remove(commentId, userid);
        } else {
            log.debug("unlike: {} have not like {}!", userid, commentId);
        }
    }

    @Override
    public boolean haveLiked(String commentId, String userid) {
        return commentLikesRepository.have(commentId, userid);
    }
}