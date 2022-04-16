package com.jigpud.snow.repository.attractionfollow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.AttractionFollowMapper;
import com.jigpud.snow.model.AttractionFollow;
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
    public void add(String follower, String attractionId) {
        AttractionFollow attractionFollow = new AttractionFollow();
        attractionFollow.setUserid(follower);
        attractionFollow.setAttractionId(attractionId);
        attractionFollowMapper.insertIgnore(attractionFollow);
    }

    @Override
    public void remove(String follower, String attractionId) {
        attractionFollowMapper.delete(followerAndAttractionQueryWrapper(follower, attractionId));
    }

    @Override
    public boolean have(String follower, String attractionId) {
        return attractionFollowMapper.exists(followerAndAttractionQueryWrapper(follower, attractionId));
    }

    @Override
    public Page<AttractionFollow> followerList(String attractionId, long pageCount, long page) {
        return attractionFollowMapper.selectPage(new Page<>(page, pageCount), attractionQueryWrapper(attractionId));
    }

    @Override
    public long followerCount(String attractionId) {
        return attractionFollowMapper.selectCount(attractionQueryWrapper(attractionId));
    }

    @Override
    public Page<AttractionFollow> followingList(String userid, long pageCount, long page) {
        return attractionFollowMapper.selectPage(new Page<>(page, pageCount), followerQueryWrapper(userid));
    }

    @Override
    public long followingCount(String userid) {
        return attractionFollowMapper.selectCount(followerQueryWrapper(userid));
    }

    private QueryWrapper<AttractionFollow> followerAndAttractionQueryWrapper(String follower, String attractionId) {
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
