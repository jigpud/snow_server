package com.jigpud.snow.response;

import com.jigpud.snow.model.User;
import com.jigpud.snow.service.favorite.FavoriteService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.like.LikeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelfInformationResponse {
    private String username;
    private String userid;
    private String nickname;
    private String avatar;
    private String background;
    private String signature;
    private String gender;
    private Integer age;
    private Long favorites;
    private Long followers;
    private Long following;
    private Long likes;

    public static SelfInformationResponse create(
            User user,
            FavoriteService favoriteService,
            FollowService followService,
            LikeService likeService
    ) {
        String userid = user.getUserid();
        SelfInformationResponse selfInfo = new SelfInformationResponse();
        selfInfo.setUsername(user.getUsername());
        selfInfo.setUserid(user.getUserid());
        selfInfo.setAvatar(user.getAvatar());
        selfInfo.setNickname(user.getNickname());
        selfInfo.setGender(user.getGender());
        selfInfo.setAge(user.getAge());
        selfInfo.setSignature(user.getSignature());
        selfInfo.setFavorites(favoriteService.storyFavorites(userid));
        selfInfo.setFollowers(followService.getUserFollowers(userid));
        selfInfo.setFollowing(followService.getFollowingCount(userid));
        selfInfo.setBackground(user.getBackground());
        selfInfo.setLikes(likeService.likes(userid));
        return selfInfo;
    }
}
