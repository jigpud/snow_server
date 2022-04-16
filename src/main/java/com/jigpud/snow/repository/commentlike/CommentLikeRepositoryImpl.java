package com.jigpud.snow.repository.commentlike;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.CommentLikeMapper;
import com.jigpud.snow.model.CommentLike;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class CommentLikeRepositoryImpl implements CommentLikeRepository {
    private final CommentLikeMapper commentLikeMapper;

    @Autowired
    CommentLikeRepositoryImpl(CommentLikeMapper commentLikeMapper) {
        this.commentLikeMapper = commentLikeMapper;
    }

    @Override
    public void add(String commentId, String userid) {
        CommentLike commentLikes = new CommentLike();
        commentLikes.setCommentId(commentId);
        commentLikes.setUserid(userid);
        commentLikeMapper.insertIgnore(commentLikes);
    }

    @Override
    public void remove(String commentId, String userid) {
        commentLikeMapper.delete(commentAndUserQueryWrapper(commentId, userid));
    }

    @Override
    public boolean have(String commentId, String userid) {
        return commentLikeMapper.exists(commentAndUserQueryWrapper(commentId, userid));
    }

    @Override
    public long commentLikes(String commentId) {
        return commentLikeMapper.selectCount(commentQueryWrapper(commentId));
    }

    @Override
    public long userLikes(String userid) {
        return commentLikeMapper.selectCount(userQueryWrapper(userid));
    }

    private QueryWrapper<CommentLike> commentAndUserQueryWrapper(String commentId, String userid) {
        QueryWrapper<CommentLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }

    private QueryWrapper<CommentLike> commentQueryWrapper(String commentId) {
        QueryWrapper<CommentLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        return queryWrapper;
    }

    private QueryWrapper<CommentLike> userQueryWrapper(String userid) {
        QueryWrapper<CommentLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }
}
