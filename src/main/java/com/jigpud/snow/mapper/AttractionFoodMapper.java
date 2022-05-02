package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.AttractionFood;
import com.jigpud.snow.model.Food;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface AttractionFoodMapper extends BaseMapper<AttractionFood> {
    void insertIgnore(AttractionFood attractionFood);

    IPage<Food> getFoodList(String attractionId, IPage<Food> page);

    IPage<Attraction> getAttractionList(String foodId, IPage<Attraction> page);
}
