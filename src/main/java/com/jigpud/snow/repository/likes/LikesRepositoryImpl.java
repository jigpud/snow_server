package com.jigpud.snow.repository.likes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.LikesMapper;
import com.jigpud.snow.model.Likes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author jigpud
 */
@Slf4j
@Repository
public class LikesRepositoryImpl implements LikesRepository {
    private final LikesMapper likesMapper;

    @Autowired
    LikesRepositoryImpl(LikesMapper likesMapper) {
        this.likesMapper = likesMapper;
    }

    @Override
    public void add(String storyId, String userid) {
        Likes likes = new Likes();
        likes.setStoryId(storyId);
        likes.setUserid(userid);
        likesMapper.insert(likes);
    }

    @Override
    public void remove(String storyId, String userid) {
        likesMapper.delete(storyAndUserQueryWrapper(storyId, userid));
    }

    @Override
    public boolean have(String storyId, String userid) {
        return likesMapper.selectOne(storyAndUserQueryWrapper(storyId, userid)) != null;
    }

    @Override
    public long userLikes(String userid) {
        return likesMapper.selectCount(userQueryWrapper(userid));
    }

    @Override
    public long storyLikes(String storyId) {
        return likesMapper.selectCount(storyQueryWrapper(storyId));
    }

    private QueryWrapper<Likes> storyAndUserQueryWrapper(String storyId, String userid) {
        QueryWrapper<Likes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }

    private QueryWrapper<Likes> storyQueryWrapper(String storyId) {
        QueryWrapper<Likes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        return queryWrapper;
    }

    private QueryWrapper<Likes> userQueryWrapper(String userid) {
        QueryWrapper<Likes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }
}
