package com.jigpud.snow.repository.attractionscore;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.AttractionScoreMapper;
import com.jigpud.snow.model.AttractionScore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class AttractionScoreRepositoryImpl implements AttractionScoreRepository {
    private final AttractionScoreMapper attractionScoreMapper;

    @Autowired
    AttractionScoreRepositoryImpl(AttractionScoreMapper attractionScoreMapper) {
        this.attractionScoreMapper = attractionScoreMapper;
    }

    @Override
    public void add(String attractionId, String userid, int score) {
        attractionScoreMapper.insertOrUpdate(attractionId, userid, score);
    }

    @Override
    public AttractionScore get(String attractionId, String userid) {
        return attractionScoreMapper.selectOne(attractionAndUserQueryWrapper(attractionId, userid));
    }

    @Override
    public void remove(String attractionId, String userid) {
        attractionScoreMapper.delete(attractionAndUserQueryWrapper(attractionId, userid));
    }

    @Override
    public boolean have(String attractionId, String userid) {
        return attractionScoreMapper.exists(attractionAndUserQueryWrapper(attractionId, userid));
    }

    @Override
    public float getAverageScore(String attractionId) {
        return attractionScoreMapper.averageScore(attractionId);
    }

    @Override
    public long scoreCount(String attractionId) {
        return attractionScoreMapper.scoreCount(attractionId);
    }

    private QueryWrapper<AttractionScore> attractionAndUserQueryWrapper(String attractionId, String userid) {
        QueryWrapper<AttractionScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("attraction_id", attractionId);
        return queryWrapper;
    }
}
