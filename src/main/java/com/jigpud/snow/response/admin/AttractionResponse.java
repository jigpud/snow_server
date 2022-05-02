package com.jigpud.snow.response.admin;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.AttractionPicture;
import com.jigpud.snow.model.AttractionTag;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.food.FoodService;
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
    private List<String> pictures;
    private List<String> tags;
    private List<FoodResponse> foods;
    private String location;
    private Float score;
    private Long storyCount;
    private Long followers;

    public static AttractionResponse create(
            Attraction attraction,
            AttractionService attractionService,
            FollowService followService,
            StoryService storyService,
            FoodService foodService
    ) {
        String attractionId = attraction.getAttractionId();
        List<String> attractionPictureList = attractionService.getPictureList(attractionId, Long.MAX_VALUE, 1).getRecords().stream()
                .map(AttractionPicture::getPicture)
                .collect(Collectors.toList());
        List<String> attractionTagList = attractionService.getTagList(attractionId, Long.MAX_VALUE, 1).getRecords().stream()
                .map(AttractionTag::getTag)
                .collect(Collectors.toList());
        List<FoodResponse> foodList = attractionService.getFoodList(attractionId, Long.MAX_VALUE, 1).getRecords().stream()
                .map(food -> FoodResponse.create(food, foodService))
                .collect(Collectors.toList());
        AttractionResponse attractionResponse = new AttractionResponse();
        attractionResponse.setAttractionId(attraction.getAttractionId());
        attractionResponse.setName(attraction.getName());
        attractionResponse.setDescription(attraction.getDescription());
        attractionResponse.setPictures(attractionPictureList);
        attractionResponse.setTags(attractionTagList);
        attractionResponse.setLocation(attraction.getLocation());
        attractionResponse.setScore(attractionService.getScore(attractionId));
        attractionResponse.setStoryCount(storyService.attractionStoryCount(attractionId));
        attractionResponse.setFollowers(followService.getAttractionFollowers(attractionId));
        attractionResponse.setFoods(foodList);
        return attractionResponse;
    }
}
