package com.jigpud.snow.repository.recommend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.RecommendAttractionMapper;
import com.jigpud.snow.mapper.RecommendUserMapper;
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
    private final RecommendAttractionMapper recommendAttractionMapper;
    private final RecommendUserMapper recommendUserMapper;

    @Autowired
    RecommendRepositoryImpl(RecommendAttractionMapper recommendAttractionMapper, RecommendUserMapper recommendUserMapper) {
        this.recommendAttractionMapper = recommendAttractionMapper;
        this.recommendUserMapper = recommendUserMapper;
    }

    @Override
    public List<Attraction> getHotAttractionList(long hotAttractionCount) {
        return recommendAttractionMapper.getRecommendAttractionList(new Page<>(1, hotAttractionCount)).getRecords();
    }

    @Override
    public Page<Attraction> getRecommendAttractionList(long pageSize, long currentPage) {
        return (Page<Attraction>) recommendAttractionMapper.getRecommendAttractionList(new Page<>(currentPage, pageSize));
    }

    @Override
    public Page<User> getRecommendUserList(String userid, long pageSize, long currentPage) {
        return (Page<User>) recommendUserMapper.getRecommendUserList(new Page<>(currentPage, pageSize), userid);
    }

    private QueryWrapper<Attraction> hotAttractionQueryWrapper() {
        QueryWrapper<Attraction> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("");
        return queryWrapper;
    }
}
