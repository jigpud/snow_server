package com.jigpud.snow.repository.food;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.FoodMapper;
import com.jigpud.snow.model.Food;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class FoodRepositoryImpl implements FoodRepository {
    private final FoodMapper foodMapper;

    @Autowired
    FoodRepositoryImpl(FoodMapper foodMapper) {
        this.foodMapper = foodMapper;
    }

    @Override
    public Food get(String foodId) {
        return foodMapper.selectOne(foodQueryWrapper(foodId));
    }

    @Override
    public Food getByName(String name) {
        return foodMapper.selectOne(nameQueryWrapper(name));
    }

    @Override
    public Page<Food> getFoodList(long pageSize, long currentPage) {
        return foodMapper.selectPage(new Page<>(currentPage, pageSize), null);
    }

    @Override
    public void add(String foodId, String name, String description) {
        Food food = new Food();
        food.setFoodId(foodId);
        food.setName(name);
        food.setDescription(description);
        foodMapper.insert(food);
    }

    @Override
    public boolean haveNameIs(String name) {
        return foodMapper.exists(nameQueryWrapper(name));
    }

    @Override
    public boolean have(String foodId) {
        return foodMapper.exists(foodQueryWrapper(foodId));
    }

    @Override
    public void remove(String foodId) {
        foodMapper.delete(foodQueryWrapper(foodId));
    }

    @Override
    public void removeByName(String name) {
        foodMapper.delete(nameQueryWrapper(name));
    }

    @Override
    public void update(String foodId, String name, String description) {
        Food food = new Food();
        food.setFoodId(foodId);
        food.setName(name);
        food.setDescription(description);
        foodMapper.update(food, foodQueryWrapper(foodId));
    }

    @Override
    public Page<Food> getFoodNameLike(String name, long pageSize, long currentPage) {
        return foodMapper.selectPage(new Page<>(currentPage, pageSize), nameLikeQueryWrapper(name));
    }

    private QueryWrapper<Food> foodQueryWrapper(String foodId) {
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("food_id", foodId);
        return queryWrapper;
    }

    private QueryWrapper<Food> nameQueryWrapper(String name) {
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return queryWrapper;
    }

    private QueryWrapper<Food> nameLikeQueryWrapper(String name) {
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return queryWrapper;
    }
}
