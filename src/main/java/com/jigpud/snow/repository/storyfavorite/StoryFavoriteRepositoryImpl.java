package com.jigpud.snow.repository.storyfavorite;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.StoryFavoriteMapper;
import com.jigpud.snow.model.StoryFavorite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class StoryFavoriteRepositoryImpl implements StoryFavoriteRepository {
    private final StoryFavoriteMapper storyFavoriteMapper;

    @Autowired
    StoryFavoriteRepositoryImpl(StoryFavoriteMapper storyFavoriteMapper) {
        this.storyFavoriteMapper = storyFavoriteMapper;
    }

    @Override
    public void add(String userid, String storyId) {
        StoryFavorite storyFavorite = new StoryFavorite();
        storyFavorite.setUserid(userid);
        storyFavorite.setStoryId(storyId);
        storyFavoriteMapper.insertIgnore(storyFavorite);
    }

    @Override
    public void remove(String userid, String storyId) {
        storyFavoriteMapper.delete(userAndStoryQueryWrapper(userid, storyId));
    }

    @Override
    public boolean have(String userid, String storyId) {
        return storyFavoriteMapper.exists(userAndStoryQueryWrapper(userid, storyId));
    }

    @Override
    public Page<StoryFavorite> favoriteList(String userid, long pageSize, long currentPage) {
        return storyFavoriteMapper.selectPage(new Page<>(currentPage, pageSize), userQueryWrapper(userid));
    }

    @Override
    public long favorites(String userid) {
        return storyFavoriteMapper.selectCount(userQueryWrapper(userid));
    }

    private QueryWrapper<StoryFavorite> userAndStoryQueryWrapper(String userid, String storyId) {
        QueryWrapper<StoryFavorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("story_id", storyId);
        return queryWrapper;
    }

    private QueryWrapper<StoryFavorite> userQueryWrapper(String userid) {
        QueryWrapper<StoryFavorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }
}
