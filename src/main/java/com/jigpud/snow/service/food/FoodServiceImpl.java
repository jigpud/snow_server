package com.jigpud.snow.service.food;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.Food;
import com.jigpud.snow.model.FoodPicture;
import com.jigpud.snow.repository.attractionfood.AttractionFoodRepository;
import com.jigpud.snow.repository.food.FoodRepository;
import com.jigpud.snow.repository.foodpicture.FoodPictureRepository;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.util.encrypt.Encryptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final AttractionFoodRepository attractionFoodRepository;
    private final FoodPictureRepository foodPictureRepository;

    @Autowired
    FoodServiceImpl(
            FoodRepository foodRepository,
            AttractionFoodRepository attractionFoodRepository,
            FoodPictureRepository foodPictureRepository
    ) {
        this.foodRepository = foodRepository;
        this.attractionFoodRepository = attractionFoodRepository;
        this.foodPictureRepository = foodPictureRepository;
    }

    @Override
    public Food getFood(String foodId) {
        return foodRepository.get(foodId);
    }

    @Override
    public boolean haveFood(String foodId) {
        return foodRepository.have(foodId);
    }

    @Override
    public void addFood(String name, String description) {
        foodRepository.add(Encryptor.uuid(), name, description);
    }

    @Override
    public void addPicture(String foodId, String userid, String picture) {
        FoodPicture foodPicture = new FoodPicture();
        foodPicture.setFoodId(foodId);
        foodPicture.setUploaderId(userid);
        foodPicture.setPicture(picture);
        foodPicture.setPictureMd5(Encryptor.md5(picture));
        foodPictureRepository.add(foodPicture);
    }

    @Override
    public void deletePicture(String foodId, String userid, String picture) {
        foodPictureRepository.remove(foodId, userid, picture);
    }

    @Override
    public void deletePicture(String foodId, String picture) {
        foodPictureRepository.remove(foodId, picture);
    }

    @Override
    public boolean havePicture(String foodId, String picture) {
        return foodPictureRepository.have(foodId, picture);
    }

    @Override
    public PageData<Food> getFoodList(long pageSize, long currentPage) {
        return PageData.fromPage(foodRepository.getFoodList(pageSize, currentPage));
    }

    @Override
    public void addAttraction(String foodId, String attractionId) {
        attractionFoodRepository.add(attractionId, foodId);
    }

    @Override
    public boolean haveAttraction(String foodId, String attractionId) {
        return attractionFoodRepository.have(attractionId, foodId);
    }

    @Override
    public PageData<Attraction> getAttractionList(String foodId, long pageSize, long currentPage) {
        return PageData.fromPage(attractionFoodRepository.getAttractionList(foodId, pageSize, currentPage));
    }

    @Override
    public PageData<FoodPicture> getPictureList(String foodId, long pageSize, long currentPage) {
        return PageData.fromPage(foodPictureRepository.getPictureList(foodId, pageSize, currentPage));
    }

    @Override
    public void deleteFood(String foodId) {
        foodRepository.remove(foodId);
    }

    @Override
    public void updateFood(String foodId, String name, String description) {
        foodRepository.update(foodId, name, description);
    }

    @Override
    public PageData<Food> getFoodNameLike(String name, long pageSize, long currentPage) {
        return PageData.fromPage(foodRepository.getFoodNameLike(name, pageSize, currentPage));
    }
}
