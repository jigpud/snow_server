package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface RecommendMapper {
    IPage<Attraction> getHotAttractionList(IPage<Attraction> page);

    IPage<Attraction> getRecommendAttractionList(IPage<Attraction> page);

    IPage<User> getRecommendUserList(IPage<User> page, String userid);
}
