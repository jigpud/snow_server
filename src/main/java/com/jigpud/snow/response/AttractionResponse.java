package com.jigpud.snow.response;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.AttractionPhoto;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.story.StoryService;
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
public class AttractionResponse {
    private String attractionId;
    private String name;
    private String description;
    private List<String> photos;
    private List<String> tags;
    private String location;
    private Float score;
    private Long scoreCount;
    private Boolean followed;
    private Long storyCount;
    private int myScore;

    public static AttractionResponse create(
            Attraction attraction,
            String self,
            AttractionService attractionService,
            FollowService followService,
            StoryService storyService
    ) {
        String attractionId = attraction.getAttractionId();
        List<String> attractionPhotoList = attractionService.getAttractionPhotoList(attractionId, 5, 1).getRecords().stream()
                .map(AttractionPhoto::getPhoto)
                .collect(Collectors.toList());
        AttractionResponse attractionResponse = new AttractionResponse();
        attractionResponse.setAttractionId(attraction.getAttractionId());
        attractionResponse.setName(attraction.getName());
        attractionResponse.setDescription(attraction.getDescription());
        attractionResponse.setPhotos(attractionPhotoList);
        attractionResponse.setTags(attraction.getTags());
        attractionResponse.setLocation(attraction.getLocation());
        attractionResponse.setScore(attractionService.getScore(attractionId));
        attractionResponse.setScoreCount(attractionService.scoreCount(attractionId));
        attractionResponse.setFollowed(followService.haveFollowingAttraction(self, attractionId));
        attractionResponse.setStoryCount(storyService.attractionStoryCount(attractionId));
        attractionResponse.setMyScore(attractionService.getUserScore(attractionId, self));
        return attractionResponse;
    }
}
