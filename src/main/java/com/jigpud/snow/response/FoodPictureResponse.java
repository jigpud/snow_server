package com.jigpud.snow.response;

import com.jigpud.snow.model.FoodPicture;
import com.jigpud.snow.model.User;
import com.jigpud.snow.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodPictureResponse {
    private String foodId;
    private String uploader;
    private String uploaderAvatar;
    private String uploaderNickname;
    private String url;

    public static FoodPictureResponse create(FoodPicture foodPicture, UserService userService) {
        User uploader = userService.getUserByUserid(foodPicture.getUploaderId());
        FoodPictureResponse foodPictureResponse = new FoodPictureResponse();
        foodPictureResponse.setFoodId(foodPicture.getFoodId());
        foodPictureResponse.setUploader(uploader.getUserid());
        foodPictureResponse.setUploaderAvatar(uploader.getAvatar());
        foodPictureResponse.setUploaderNickname(uploader.getNickname());
        foodPictureResponse.setUrl(foodPicture.getPicture());
        return foodPictureResponse;
    }
}
