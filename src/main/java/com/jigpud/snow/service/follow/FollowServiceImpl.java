package com.jigpud.snow.service.follow;

import com.jigpud.snow.model.AttractionFollow;
import com.jigpud.snow.model.UserFollow;
import com.jigpud.snow.repository.attractionfollow.AttractionFollowRepository;
import com.jigpud.snow.repository.userfollow.UserFollowRepository;
import com.jigpud.snow.util.response.PageData;
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
    public void followAttraction(String follower, String attractionId) {
        attractionFollowRepository.add(follower, attractionId);
    }

    @Override
    public void unfollowAttraction(String follower, String attractionId) {
        attractionFollowRepository.remove(follower, attractionId);
    }

    @Override
    public long followerCount(String userid) {
        return userFollowRepository.followerCount(userid);
    }

    @Override
    public PageData<String> followerList(String userid, long pageCount, long page) {
        return PageData.fromPage(userFollowRepository.followerList(userid, pageCount, page), UserFollow::getFollowerId);
    }

    @Override
    public long userFollowingCount(String userid) {
        return userFollowRepository.followingCount(userid);
    }

    @Override
    public PageData<String> userFollowingList(String userid, long pageCount, long page) {
        return PageData.fromPage(userFollowRepository.followingList(userid, pageCount, page), UserFollow::getUserid);
    }

    @Override
    public long attractionFollowingCount(String userid) {
        return attractionFollowRepository.followingCount(userid);
    }

    @Override
    public PageData<String> attractionFollowingList(String userid, long pageCount, long page) {
        return PageData.fromPage(attractionFollowRepository.followingList(userid, pageCount, page), AttractionFollow::getAttractionId);
    }

    @Override
    public boolean haveFollowingUser(String follower, String userid) {
        return userFollowRepository.have(follower, userid);
    }

    @Override
    public boolean haveFollowingAttraction(String follower, String attractionId) {
        return attractionFollowRepository.have(follower, attractionId);
    }

    @Override
    public long followingCount(String userid) {
        return userFollowRepository.followingCount(userid) + attractionFollowRepository.followingCount(userid);
    }
}
