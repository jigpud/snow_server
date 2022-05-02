package com.jigpud.snow.repository.foodpicture;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.FoodPictureMapper;
import com.jigpud.snow.model.FoodPicture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class FoodPictureRepositoryImpl implements FoodPictureRepository {
    private final FoodPictureMapper foodPictureMapper;
    
    @Autowired
    FoodPictureRepositoryImpl(FoodPictureMapper foodPictureMapper) {
        this.foodPictureMapper = foodPictureMapper;
    }
    
    @Override
    public void add(FoodPicture foodPicture) {
        foodPictureMapper.insertIgnore(foodPicture);
    }

    @Override
    public boolean have(String foodId, String picture) {
        return foodPictureMapper.exists(foodAndPictureQueryWrapper(foodId, picture));
    }

    @Override
    public void remove(String foodId, String uploaderId, String picture) {
        foodPictureMapper.delete(foodUploaderAndPictureQueryWrapper(foodId, uploaderId, picture));
    }

    @Override
    public void remove(String foodId, String picture) {
        foodPictureMapper.delete(foodAndPictureQueryWrapper(foodId, picture));
    }

    @Override
    public Page<FoodPicture> getPictureList(String foodId, long pageSize, long currentPage) {
        return foodPictureMapper.selectPage(new Page<>(currentPage, pageSize), foodQueryWrapper(foodId));
    }

    private QueryWrapper<FoodPicture> foodUploaderAndPictureQueryWrapper(String foodId, String uploaderId, String picture) {
        QueryWrapper<FoodPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("food_id", foodId);
        queryWrapper.eq("uploader_id", uploaderId);
        queryWrapper.eq("picture", picture);
        return queryWrapper;
    }
    
    private QueryWrapper<FoodPicture> foodAndPictureQueryWrapper(String foodId, String picture) {
        QueryWrapper<FoodPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("food_id", foodId);
        queryWrapper.eq("picture", picture);
        return queryWrapper;
    }
    
    private QueryWrapper<FoodPicture> foodQueryWrapper(String foodId) {
        QueryWrapper<FoodPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("food_id", foodId);
        return queryWrapper;
    }
}
