package com.jigpud.snow.repository.comment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.CommentMapper;
import com.jigpud.snow.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentMapper commentMapper;

    @Autowired
    CommentRepositoryImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public Comment getComment(String commentId) {
        return commentMapper.selectOne(commentQueryWrapper(commentId));
    }

    @Override
    public Page<Comment> getUserCommentList(String userid, long pageSize, long currentPage) {
        return commentMapper.selectPage(new Page<>(currentPage, pageSize), authorQueryWrapper(userid));
    }

    @Override
    public Page<Comment> getStoryCommentList(String storyId, long pageSize, long currentPage) {
        return commentMapper.selectPage(new Page<>(currentPage, pageSize), storyQueryWrapper(storyId));
    }

    @Override
    public Page<Comment> getCommentReplyList(String commentId, long pageSize, long currentPage) {
        return commentMapper.selectPage(new Page<>(currentPage, pageSize), replyQueryWrapper(commentId));
    }

    private QueryWrapper<Comment> commentQueryWrapper(String commentId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        return queryWrapper;
    }

    private QueryWrapper<Comment> authorQueryWrapper(String authorId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id", authorId);
        return queryWrapper;
    }

    private QueryWrapper<Comment> storyQueryWrapper(String storyId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        return queryWrapper;
    }

    private QueryWrapper<Comment> replyQueryWrapper(String replyTo) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", replyTo);
        return queryWrapper;
    }
}
