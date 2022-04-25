package com.jigpud.snow.repository.story;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.StoryMapper;
import com.jigpud.snow.model.Story;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
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
        return storyMapper.selectOne(storyQueryWrapper(storyId));
    }

    @Override
    public Page<Story> getUserStoryList(String userid, long pageSize, long currentPage) {
        QueryWrapper<Story> queryWrapper = authorQueryWrapper(userid);
        queryWrapper.orderByDesc("release_time");
        return storyMapper.selectPage(new Page<>(currentPage, pageSize), queryWrapper);
    }

    @Override
    public void update(Story story) {
        String storyId = story.getStoryId();
        storyMapper.update(story, storyQueryWrapper(storyId));
    }

    @Override
    public Page<Story> blurSearch(String keyWords, long pageSize, long currentPage) {
        return storyMapper.selectPage(new Page<>(currentPage, pageSize), blurQueryWrapper(keyWords));
    }

    @Override
    public long userStoryCount(String userid) {
        return storyMapper.selectCount(authorQueryWrapper(userid));
    }

    @Override
    public long attractionStoryCount(String attractionId) {
        return storyMapper.selectCount(attractionQueryWrapper(attractionId));
    }

    private QueryWrapper<Story> storyQueryWrapper(String storyId) {
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        return queryWrapper;
    }

    private QueryWrapper<Story> authorQueryWrapper(String authorId) {
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id", authorId);
        return queryWrapper;
    }

    private QueryWrapper<Story> blurQueryWrapper(String keyWords) {
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", keyWords)
                .or()
                .like("content", keyWords);
        return queryWrapper;
    }

    private QueryWrapper<Story> attractionQueryWrapper(String attractionId) {
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        return queryWrapper;
    }
}
