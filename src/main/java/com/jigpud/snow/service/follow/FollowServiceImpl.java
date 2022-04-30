package com.jigpud.snow.service.follow;

import com.jigpud.snow.model.AttractionFollow;
import com.jigpud.snow.model.UserFollow;
import com.jigpud.snow.repository.attractionfollow.AttractionFollowRepository;
import com.jigpud.snow.repository.userfollow.UserFollowRepository;
import com.jigpud.snow.response.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class FollowServiceImpl implements FollowService {
    private final UserFollowRepository userFollowRepository;
    private final AttractionFollowRepository attractionFollowRepository;

    @Autowired
    FollowServiceImpl(UserFollowRepository userFollowRepository, AttractionFollowRepository attractionFollowRepository) {
        this.userFollowRepository = userFollowRepository;
        this.attractionFollowRepository = attractionFollowRepository;
    }

    @Override
    public void followUser(String follower, String userid) {
        userFollowRepository.add(follower, userid);
    }

    @Override
    public void unfollowUser(String follower, String userid) {
        userFollowRepository.remove(follower, userid);
    }

    @Override
    public void followAttraction(String attractionId, String follower) {
        attractionFollowRepository.add(attractionId, follower);
    }

    @Override
    public void unfollowAttraction(String attractionId, String follower) {
        attractionFollowRepository.remove(attractionId, follower);
    }

    @Override
    public long userFollowerCount(String userid) {
        return userFollowRepository.followerCount(userid);
    }

    @Override
    public PageData<String> followerList(String userid, long pageSize, long currentPage) {
        return PageData.fromPage(userFollowRepository.followerList(userid, pageSize, currentPage), UserFollow::getFollowerId);
    }

    @Override
    public long userFollowingCount(String userid) {
        return userFollowRepository.followingCount(userid);
    }

    @Override
    public PageData<String> userFollowingList(String userid, long pageSize, long currentPage) {
        return PageData.fromPage(userFollowRepository.followingList(userid, pageSize, currentPage), UserFollow::getUserid);
    }

    @Override
    public long attractionFollowingCount(String userid) {
        return attractionFollowRepository.followingCount(userid);
    }

    @Override
    public long attractionFollowerCount(String attractionId) {
        return attractionFollowRepository.followerCount(attractionId);
    }

    @Override
    public PageData<String> attractionFollowingList(String userid, long pageSize, long currentPage) {
        return PageData.fromPage(attractionFollowRepository.followingList(userid, pageSize, currentPage), AttractionFollow::getAttractionId);
    }

    @Override
    public boolean haveFollowingUser(String follower, String userid) {
        return userFollowRepository.have(follower, userid);
    }

    @Override
    public boolean haveFollowingAttraction(String attractionId, String follower) {
        return attractionFollowRepository.have(attractionId, follower);
    }

    @Override
    public long followingCount(String userid) {
        return userFollowRepository.followingCount(userid) + attractionFollowRepository.followingCount(userid);
    }
}
