package com.jigpud.snow.repository.storylike;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.StoryLikeMapper;
import com.jigpud.snow.model.StoryLike;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class StoryLikeRepositoryImpl implements StoryLikeRepository {
    private final StoryLikeMapper storyLikeMapper;

    @Autowired
    StoryLikeRepositoryImpl(StoryLikeMapper likesMapper) {
        this.storyLikeMapper = likesMapper;
    }

    @Override
    public void add(String storyId, String userid) {
        StoryLike likes = new StoryLike();
        likes.setStoryId(storyId);
        likes.setUserid(userid);
        storyLikeMapper.insertIgnore(likes);
    }

    @Override
    public void remove(String storyId, String userid) {
        storyLikeMapper.delete(storyAndUserQueryWrapper(storyId, userid));
    }

    @Override
    public boolean have(String storyId, String userid) {
        return storyLikeMapper.exists(storyAndUserQueryWrapper(storyId, userid));
    }

    @Override
    public long userLikes(String userid) {
        return storyLikeMapper.selectCount(userQueryWrapper(userid));
    }

    @Override
    public long storyLikes(String storyId) {
        return storyLikeMapper.selectCount(storyQueryWrapper(storyId));
    }

    private QueryWrapper<StoryLike> storyAndUserQueryWrapper(String storyId, String userid) {
        QueryWrapper<StoryLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }

    private QueryWrapper<StoryLike> storyQueryWrapper(String storyId) {
        QueryWrapper<StoryLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        return queryWrapper;
    }

    private QueryWrapper<StoryLike> userQueryWrapper(String userid) {
        QueryWrapper<StoryLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }
}
