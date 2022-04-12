package com.jigpud.snow.repository.commentlikes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.CommentLikesMapper;
import com.jigpud.snow.model.CommentLikes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class CommentLikesRepositoryImpl implements CommentLikesRepository{
    private final CommentLikesMapper commentLikesMapper;

    @Autowired
    CommentLikesRepositoryImpl(CommentLikesMapper commentLikesMapper) {
        this.commentLikesMapper = commentLikesMapper;
    }

    @Override
    public void add(String commentId, String userid) {
        CommentLikes commentLikes = new CommentLikes();
        commentLikes.setCommentId(commentId);
        commentLikes.setUserid(userid);
        commentLikesMapper.insertIgnore(commentLikes);
    }

    @Override
    public void remove(String commentId, String userid) {
        commentLikesMapper.delete(commentAndUserQueryWrapper(commentId, userid));
    }

    @Override
    public boolean have(String commentId, String userid) {
        return commentLikesMapper.exists(commentAndUserQueryWrapper(commentId, userid));
    }

    @Override
    public long commentLikes(String commentId) {
        return commentLikesMapper.selectCount(commentQueryWrapper(commentId));
    }

    private QueryWrapper<CommentLikes> commentAndUserQueryWrapper(String commentId, String userid) {
        QueryWrapper<CommentLikes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }

    private QueryWrapper<CommentLikes> commentQueryWrapper(String commentId) {
        QueryWrapper<CommentLikes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        return queryWrapper;
    }
}
