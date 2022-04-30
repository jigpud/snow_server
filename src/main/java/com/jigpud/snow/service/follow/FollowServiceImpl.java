package com.jigpud.snow.service.follow;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.User;
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
    public long getUserFollowers(String userid) {
        return userFollowRepository.getFollowers(userid);
    }

    @Override
    public PageData<User> getUserFollowerList(String userid, long pageSize, long currentPage) {
        return PageData.fromPage(userFollowRepository.getFollowerList(userid, pageSize, currentPage));
    }

    @Override
    public long getFollowingUserCount(String userid) {
        return userFollowRepository.getFollowingCount(userid);
    }

    @Override
    public PageData<User> getFollowingUserList(String userid, long pageSize, long currentPage) {
        return PageData.fromPage(userFollowRepository.getFollowingList(userid, pageSize, currentPage));
    }

    @Override
    public long getFollowingAttractionCount(String userid) {
        return attractionFollowRepository.getFollowingCount(userid);
    }

    @Override
    public long getAttractionFollowers(String attractionId) {
        return attractionFollowRepository.getFollowers(attractionId);
    }

    @Override
    public PageData<Attraction> getFollowingAttractionList(String userid, long pageSize, long currentPage) {
        return PageData.fromPage(attractionFollowRepository.getFollowingList(userid, pageSize, currentPage));
    }

    @Override
    public boolean haveFollowedUser(String follower, String userid) {
        return userFollowRepository.have(follower, userid);
    }

    @Override
    public boolean haveFollowedAttraction(String attractionId, String follower) {
        return attractionFollowRepository.have(attractionId, follower);
    }

    @Override
    public long getFollowingCount(String userid) {
        return userFollowRepository.getFollowingCount(userid) + attractionFollowRepository.getFollowingCount(userid);
    }
}
