package com.jigpud.snow.repository.storypicture;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.StoryPictureMapper;
import com.jigpud.snow.model.FoodPicture;
import com.jigpud.snow.model.StoryPicture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class StoryPictureRepositoryImpl implements StoryPictureRepository {
    private final StoryPictureMapper storyPictureMapper;

    @Autowired
    StoryPictureRepositoryImpl(StoryPictureMapper storyPictureMapper) {
        this.storyPictureMapper = storyPictureMapper;
    }

    @Override
    public void add(StoryPicture storyPicture) {
        storyPictureMapper.insertIgnore(storyPicture);
    }

    @Override
    public boolean have(String storyId, String picture) {
        return storyPictureMapper.exists(storyAndPictureQueryWrapper(storyId, picture));
    }

    @Override
    public void remove(String storyId, String picture) {
        storyPictureMapper.delete(storyAndPictureQueryWrapper(storyId, picture));
    }

    @Override
    public void remove(String storyId, String uploaderId, String picture) {
        storyPictureMapper.delete(storyUploaderAndPictureQueryWrapper(storyId, uploaderId, picture));
    }

    @Override
    public Page<StoryPicture> getPictureList(String storyId, long pageSize, long currentPage) {
        return storyPictureMapper.selectPage(new Page<>(currentPage, pageSize), storyQueryWrapper(storyId));
    }

    private QueryWrapper<StoryPicture> storyUploaderAndPictureQueryWrapper(String storyId, String uploaderId, String picture) {
        QueryWrapper<StoryPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        queryWrapper.eq("uploader_id", uploaderId);
        queryWrapper.eq("picture", picture);
        return queryWrapper;
    }

    private QueryWrapper<StoryPicture> storyAndPictureQueryWrapper(String storyId, String picture) {
        QueryWrapper<StoryPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        queryWrapper.eq("picture", picture);
        return queryWrapper;
    }

    private QueryWrapper<StoryPicture> storyQueryWrapper(String storyId) {
        QueryWrapper<StoryPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("story_id", storyId);
        return queryWrapper;
    }
}
