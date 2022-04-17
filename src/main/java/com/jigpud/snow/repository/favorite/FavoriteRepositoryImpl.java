package com.jigpud.snow.repository.favorite;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.FavoriteMapper;
import com.jigpud.snow.model.Favorite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository {
    private final FavoriteMapper favoriteMapper;

    @Autowired
    FavoriteRepositoryImpl(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }

    @Override
    public void add(String userid, String storyId) {
        Favorite favorite = new Favorite();
        favorite.setUserid(userid);
        favorite.setStoryId(storyId);
        favoriteMapper.insertIgnore(favorite);
    }

    @Override
    public void remove(String userid, String storyId) {
        favoriteMapper.delete(userAndStoryQueryWrapper(userid, storyId));
    }

    @Override
    public boolean have(String userid, String storyId) {
        return favoriteMapper.exists(userAndStoryQueryWrapper(userid, storyId));
    }

    @Override
    public Page<Favorite> favoriteList(String userid, long pageSize, long currentPage) {
        return favoriteMapper.selectPage(new Page<>(currentPage, pageSize), userQueryWrapper(userid));
    }

    @Override
    public long favorites(String userid) {
        return favoriteMapper.selectCount(userQueryWrapper(userid));
    }

    private QueryWrapper<Favorite> userAndStoryQueryWrapper(String userid, String storyId) {
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("story_id", storyId);
        return queryWrapper;
    }

    private QueryWrapper<Favorite> userQueryWrapper(String userid) {
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }
}
