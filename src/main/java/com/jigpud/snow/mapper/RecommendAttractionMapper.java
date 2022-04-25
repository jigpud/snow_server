package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jigpud.snow.model.Attraction;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface RecommendAttractionMapper extends BaseMapper<Attraction> {
    IPage<Attraction> getRecommendAttractionList(IPage<Attraction> page);
}
