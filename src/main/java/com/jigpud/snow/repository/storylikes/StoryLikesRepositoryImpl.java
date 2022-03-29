package com.jigpud.snow.repository.storylikes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.StoryLikesMapper;
import com.jigpud.snow.model.StoryLikes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class StoryLikesRepositoryImpl implements StoryLikesRepository {
    private final StoryLikesMapper storyLikesMapper;

    @Autowired
    StoryLikesRepositoryImpl(StoryLikesMapper likesMapper) {
        this.storyLikesMapper = likesMapper;
    }

    @Override
    public void add(String storyId, String userid) {
        StoryLikes likes = new StoryLikes();
        likes.setStoryId(storyId);
        likes.setUserid(userid);
        storyLikesMapper.insert(likes);
    }

    @Override
    public void remove(String storyId, String userid) {
        storyLikesMapper.delete(storyAndUserQueryWrapper(storyId, userid));
    }

    @Override
    public boolean have(String storyId, String userid) {
        return storyLikesMapper.selectOne(storyAndUserQueryWrapper(storyId, userid)) != null;
    }

    @Override
    public long userLikes(String userid) {
        return storyLikesMapper.selectCount(userQueryWrapper(userid));
    }

    @Override
    public long storyLikes(String storyId) {
        return storyLikesMapper.selectCount(storyQueryWrapper(storyId));
    }

    private QueryWrapper<StoryLikes> storyAndUserQueryWrapper(String storyId, String userid) {
        QueryWrapper<StoryLikes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }

    private QueryWrapper<StoryLikes> storyQueryWrapper(String storyId) {
        QueryWrapper<StoryLikes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        return queryWrapper;
    }

    private QueryWrapper<StoryLikes> userQueryWrapper(String userid) {
        QueryWrapper<StoryLikes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }
}
