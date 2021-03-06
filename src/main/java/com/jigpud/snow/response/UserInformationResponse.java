package com.jigpud.snow.response;

import com.jigpud.snow.model.User;
import com.jigpud.snow.service.favorite.FavoriteService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.like.LikeService;
import com.jigpud.snow.service.story.StoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInformationResponse {
    private String userid;
    private String nickname;
    private String avatar;
    private String background;
    private String signature;
    private String gender;
    private Integer age;
    private Long likes;
    private Long followers;
    private Long following;
    private Boolean followed;
    private Long storyCount;
    private Long favorites;

    public static UserInformationResponse create(
            User user,
            String self,
            FollowService followService,
            LikeService likeService,
            StoryService storyService,
            FavoriteService favoriteService
    ) {
        String userid = user.getUserid();
        UserInformationResponse userInfo = new UserInformationResponse();
        userInfo.setUserid(user.getUserid());
        userInfo.setNickname(user.getNickname());
        userInfo.setBackground(user.getBackground());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setGender(user.getGender());
        userInfo.setAge(user.getAge());
        userInfo.setSignature(user.getSignature());
        userInfo.setLikes(likeService.likes(userid));
        userInfo.setFollowers(followService.getUserFollowers(userid));
        userInfo.setFollowing(followService.getFollowingCount(userid));
        userInfo.setFollowed(followService.haveFollowedUser(self, userid));
        userInfo.setStoryCount(storyService.userStoryCount(userid));
        userInfo.setFavorites(favoriteService.storyFavorites(userid));
        return userInfo;
    }
}
