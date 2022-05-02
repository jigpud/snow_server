package com.jigpud.snow.response.admin;

import com.jigpud.snow.model.Food;
import com.jigpud.snow.model.FoodPicture;
import com.jigpud.snow.service.food.FoodService;
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
public class FoodResponse {
    private String foodId;
    private String name;
    private String description;
    private List<String> pictures;

    public static FoodResponse create(Food food, FoodService foodService) {
        String foodId = food.getFoodId();
        List<String> foodPictureList = foodService.getPictureList(foodId, Long.MAX_VALUE, 1).getRecords().stream()
                .map(FoodPicture::getPicture)
                .collect(Collectors.toList());
        FoodResponse foodResponse = new FoodResponse();
        foodResponse.setFoodId(foodId);
        foodResponse.setName(food.getName());
        foodResponse.setDescription(food.getDescription());
        foodResponse.setPictures(foodPictureList);
        return foodResponse;
    }
}
