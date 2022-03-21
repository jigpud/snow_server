package com.jigpud.snow.repository.story;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.StoryMapper;
import com.jigpud.snow.model.Story;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author jigpud
 */
@Slf4j
@Repository
public class StoryRepositoryImpl implements StoryRepository {
    private final StoryMapper storyMapper;

    @Autowired
    StoryRepositoryImpl(StoryMapper storyMapper) {
        this.storyMapper = storyMapper;
    }

    @Override
    public void addStory(Story story) {
        storyMapper.insert(story);
    }

    @Override
    public Story getStory(String storyId) {
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        return storyMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<Story> getUserStoryList(String userid, long pageCount, long page) {
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id", userid);
        return storyMapper.selectPage(new Page<>(page, pageCount), queryWrapper);
    }

    @Override
    public void update(Story story) {
        String storyId = story.getStoryId();
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        storyMapper.update(story, queryWrapper);
    }
}
