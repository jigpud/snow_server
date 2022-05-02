package com.jigpud.snow.repository.attractionfood;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.AttractionFoodMapper;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.AttractionFood;
import com.jigpud.snow.model.Food;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class AttractionFoodRepositoryImpl implements AttractionFoodRepository {
    private final AttractionFoodMapper attractionFoodMapper;

    @Autowired
    AttractionFoodRepositoryImpl(AttractionFoodMapper attractionFoodMapper) {
        this.attractionFoodMapper = attractionFoodMapper;
    }

    @Override
    public void add(String attractionId, String foodId) {
        AttractionFood attractionFood = new AttractionFood();
        attractionFood.setAttractionId(attractionId);
        attractionFood.setFoodId(foodId);
        attractionFoodMapper.insertIgnore(attractionFood);
    }

    @Override
    public boolean have(String attractionId, String foodId) {
        return attractionFoodMapper.exists(attractionAndFoodQueryWrapper(attractionId, foodId));
    }

    @Override
    public void remove(String attractionId, String foodId) {
        attractionFoodMapper.delete(attractionAndFoodQueryWrapper(attractionId, foodId));
    }

    @Override
    public Page<Food> getFoodList(String attractionId, long pageSize, long currentPage) {
        return (Page<Food>) attractionFoodMapper.getFoodList(attractionId, new Page<>(currentPage, pageSize));
    }

    @Override
    public Page<Attraction> getAttractionList(String foodId, long pageSize, long currentPage) {
        return (Page<Attraction>) attractionFoodMapper.getAttractionList(foodId, new Page<>(currentPage, pageSize));
    }

    private QueryWrapper<AttractionFood> attractionAndFoodQueryWrapper(String attractionId, String foodId) {
        QueryWrapper<AttractionFood> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        queryWrapper.eq("food_id", foodId);
        return queryWrapper;
    }
}
