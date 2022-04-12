package com.jigpud.snow.repository.follow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.FollowMapper;
import com.jigpud.snow.model.Follow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class FollowRepositoryImpl implements FollowRepository {
    private final FollowMapper followMapper;

    @Autowired
    FollowRepositoryImpl(FollowMapper followMapper) {
        this.followMapper = followMapper;
    }

    @Override
    public void add(String follower, String userid) {
        Follow follow = new Follow();
        follow.setFollowerId(follower);
        follow.setUserid(userid);
        followMapper.insertIgnore(follow);
    }

    @Override
    public void remove(String follower, String userid) {
        followMapper.delete(followerAndUserQueryWrapper(follower, userid));
    }

    @Override
    public boolean have(String follower, String userid) {
        return followMapper.exists(followerAndUserQueryWrapper(follower, userid));
    }

    @Override
    public Page<Follow> followerList(String userid, long pageCount, long page) {
        return followMapper.selectPage(new Page<>(page, pageCount), userQueryWrapper(userid));
    }

    @Override
    public long followerCount(String userid) {
        return followMapper.selectCount(userQueryWrapper(userid));
    }

    @Override
    public Page<Follow> followedList(String userid, long pageCount, long page) {
        return followMapper.selectPage(new Page<>(page, pageCount), followerQueryWrapper(userid));
    }

    @Override
    public long followedCount(String userid) {
        return followMapper.selectCount(followerQueryWrapper(userid));
    }

    private QueryWrapper<Follow> followerAndUserQueryWrapper(String follower, String userid) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("follower_id", follower);
        return queryWrapper;
    }

    private QueryWrapper<Follow> followerQueryWrapper(String follower) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("follower_id", follower);
        return queryWrapper;
    }

    private QueryWrapper<Follow> userQueryWrapper(String userid) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }
}
