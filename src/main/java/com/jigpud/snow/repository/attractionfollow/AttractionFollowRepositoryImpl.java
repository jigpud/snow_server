package com.jigpud.snow.repository.attractionfollow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.AttractionFollowMapper;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.AttractionFollow;
import com.jigpud.snow.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class AttractionFollowRepositoryImpl implements AttractionFollowRepository {
    private final AttractionFollowMapper attractionFollowMapper;

    @Autowired
    AttractionFollowRepositoryImpl(AttractionFollowMapper attractionFollowMapper) {
        this.attractionFollowMapper = attractionFollowMapper;
    }

    @Override
    public void add(String attractionId, String follower) {
        AttractionFollow attractionFollow = new AttractionFollow();
        attractionFollow.setUserid(follower);
        attractionFollow.setAttractionId(attractionId);
        attractionFollowMapper.insertIgnore(attractionFollow);
    }

    @Override
    public void remove(String attractionId, String follower) {
        attractionFollowMapper.delete(attractionAndFollowerQueryWrapper(attractionId, follower));
    }

    @Override
    public boolean have(String attractionId, String follower) {
        return attractionFollowMapper.exists(attractionAndFollowerQueryWrapper(attractionId, follower));
    }

    @Override
    public Page<User> getFollowerList(String attractionId, long pageSize, long currentPage) {
        return (Page<User>) attractionFollowMapper.getFollowerList(attractionId, new Page<>(currentPage, pageSize));
    }

    @Override
    public long getFollowers(String attractionId) {
        return attractionFollowMapper.selectCount(attractionQueryWrapper(attractionId));
    }

    @Override
    public Page<Attraction> getFollowingList(String userid, long pageSize, long currentPage) {
        return (Page<Attraction>) attractionFollowMapper.getFollowingList(userid, new Page<>(currentPage, pageSize));
    }

    @Override
    public long getFollowingCount(String userid) {
        return attractionFollowMapper.selectCount(followerQueryWrapper(userid));
    }

    private QueryWrapper<AttractionFollow> attractionAndFollowerQueryWrapper(String attractionId, String follower) {
        QueryWrapper<AttractionFollow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", follower);
        queryWrapper.eq("attraction_id", attractionId);
        return queryWrapper;
    }

    private QueryWrapper<AttractionFollow> followerQueryWrapper(String follower) {
        QueryWrapper<AttractionFollow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", follower);
        return queryWrapper;
    }

    private QueryWrapper<AttractionFollow> attractionQueryWrapper(String attractionId) {
        QueryWrapper<AttractionFollow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        return queryWrapper;
    }
}
