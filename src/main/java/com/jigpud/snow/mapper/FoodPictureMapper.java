package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.FoodPicture;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface FoodPictureMapper extends BaseMapper<FoodPicture> {
    void insertIgnore(FoodPicture foodPicture);
}
