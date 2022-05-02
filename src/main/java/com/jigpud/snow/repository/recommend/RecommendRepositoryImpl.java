package com.jigpud.snow.repository.recommend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.RecommendMapper;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class RecommendRepositoryImpl implements RecommendRepository {
    private final RecommendMapper recommendMapper;

    @Autowired
    RecommendRepositoryImpl(RecommendMapper recommendMapper) {
        this.recommendMapper = recommendMapper;
    }

    @Override
    public Page<Attraction> getHotAttractionList(long pageSize, long currentPage) {
        return (Page<Attraction>) recommendMapper.getHotAttractionList(new Page<>(currentPage, pageSize));
    }

    @Override
    public Page<Attraction> getRecommendAttractionList(long pageSize, long currentPage) {
        return (Page<Attraction>) recommendMapper.getRecommendAttractionList(new Page<>(currentPage, pageSize));
    }

    @Override
    public Page<User> getRecommendUserList(String userid, long pageSize, long currentPage) {
        return (Page<User>) recommendMapper.getRecommendUserList(new Page<>(currentPage, pageSize), userid);
    }
}
