package com.jigpud.snow.repository.userfollow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.UserFollowMapper;
import com.jigpud.snow.model.UserFollow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class UserFollowRepositoryImpl implements UserFollowRepository {
    private final UserFollowMapper followMapper;

    @Autowired
    UserFollowRepositoryImpl(UserFollowMapper followMapper) {
        this.followMapper = followMapper;
    }

    @Override
    public void add(String follower, String userid) {
        UserFollow userFollow = new UserFollow();
        userFollow.setFollowerId(follower);
        userFollow.setUserid(userid);
        followMapper.insertIgnore(userFollow);
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
    public Page<UserFollow> followerList(String userid, long pageCount, long page) {
        return followMapper.selectPage(new Page<>(page, pageCount), userQueryWrapper(userid));
    }

    @Override
    public long followerCount(String userid) {
        return followMapper.selectCount(userQueryWrapper(userid));
    }

    @Override
    public Page<UserFollow> followingList(String userid, long pageCount, long page) {
        return followMapper.selectPage(new Page<>(page, pageCount), followerQueryWrapper(userid));
    }

    @Override
    public long followingCount(String userid) {
        return followMapper.selectCount(followerQueryWrapper(userid));
    }

    private QueryWrapper<UserFollow> followerAndUserQueryWrapper(String follower, String userid) {
        QueryWrapper<UserFollow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("follower_id", follower);
        return queryWrapper;
    }

    private QueryWrapper<UserFollow> followerQueryWrapper(String follower) {
        QueryWrapper<UserFollow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("follower_id", follower);
        return queryWrapper;
    }

    private QueryWrapper<UserFollow> userQueryWrapper(String userid) {
        QueryWrapper<UserFollow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }
}
