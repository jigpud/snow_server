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
    public void add(String userid, String attractionId, int score) {
        attractionScoreMapper.insertIgnore(userid, attractionId, score);
    }

    @Override
    public void remove(String userid, String attractionId) {
        attractionScoreMapper.delete(userAndAttractionQueryWrapper(userid, attractionId));
    }

    @Override
    public boolean have(String userid, String attractionId) {
        return attractionScoreMapper.exists(userAndAttractionQueryWrapper(userid, attractionId));
    }

    @Override
    public float getAttractionAverageScore(String attractionId) {
        return attractionScoreMapper.averageScore(attractionId);
    }

    @Override
    public long scoreCount(String attractionId) {
        return attractionScoreMapper.scoreCount(attractionId);
    }

    private QueryWrapper<AttractionScore> userAndAttractionQueryWrapper(String userid, String attractionId) {
        QueryWrapper<AttractionScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("attraction_id", attractionId);
        return queryWrapper;
    }

    private QueryWrapper<AttractionScore> attractionQueryWrapper(String attractionId) {
        QueryWrapper<AttractionScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        return queryWrapper;
    }
}
