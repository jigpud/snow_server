package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.AttractionScore;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface AttractionScoreMapper extends BaseMapper<AttractionScore> {
    void insertOrUpdate(String attractionId, String userid, int score);

    float averageScore(String attractionId);

    long scoreCount(String attractionId);
}
