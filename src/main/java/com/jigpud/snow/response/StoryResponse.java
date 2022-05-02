package com.jigpud.snow.response;

import com.jigpud.snow.model.Story;
import com.jigpud.snow.model.StoryPicture;
import com.jigpud.snow.model.User;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.favorite.FavoriteService;
import com.jigpud.snow.service.like.LikeService;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoryResponse {
    private String storyId;
    private String authorId;
    private String authorNickname;
    private String authorAvatar;
    private String title;
    private String content;
    private Long likes;
    private List<String> pictures;
    private Long releaseTime;
    private String releaseLocation;
    private Boolean liked;
    private String attractionId;
    private Boolean haveFavorite;
    private Long favorites;

    public static StoryResponse create(
            Story story,
            String self,
            StoryService storyService,
            UserService userService,
            LikeService likeService,
            AttractionService attractionService,
            FavoriteService favoriteService
    ) {
        String storyId = story.getStoryId();
        User author = userService.getUserByUserid(story.getAuthorId());
        List<String> storyPictureList = storyService.getPictureList(storyId, Long.MAX_VALUE, 1).getRecords().stream()
                .map(StoryPicture::getPicture)
                .collect(Collectors.toList());
        StoryResponse storyResponse = new StoryResponse();
        storyResponse.setStoryId(storyId);
        storyResponse.setAuthorId(author.getUserid());
        storyResponse.setAuthorNickname(author.getNickname());
        storyResponse.setAuthorAvatar(author.getAvatar());
        storyResponse.setTitle(story.getTitle());
        storyResponse.setContent(story.getContent());
        storyResponse.setPictures(storyPictureList);
        storyResponse.setReleaseTime(story.getReleaseTime());
        storyResponse.setReleaseLocation(attractionService.getAttraction(story.getAttractionId()).getName());
        storyResponse.setAttractionId(story.getAttractionId());
        storyResponse.setLiked(likeService.haveLikedStory(storyId, self));
        storyResponse.setLikes(likeService.storyLikes(storyId));
        storyResponse.setHaveFavorite(favoriteService.haveFavoriteStory(storyId, self));
        storyResponse.setFavorites(favoriteService.storyFavorites(storyId));
        return storyResponse;
    }
}
