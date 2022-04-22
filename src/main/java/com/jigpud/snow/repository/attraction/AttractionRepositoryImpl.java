package com.jigpud.snow.repository.attraction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.AttractionMapper;
import com.jigpud.snow.model.Attraction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class AttractionRepositoryImpl implements AttractionRepository {
    private final AttractionMapper attractionMapper;

    @Autowired
    AttractionRepositoryImpl(AttractionMapper attractionMapper) {
        this.attractionMapper = attractionMapper;
    }

    @Override
    public void add(Attraction attraction) {
        attractionMapper.insert(attraction);
    }

    @Override
    public void update(Attraction attraction) {
        attractionMapper.update(attraction, attractionQueryWrapper(attraction.getAttractionId()));
    }

    @Override
    public void remove(String attractionId) {
        attractionMapper.delete(attractionQueryWrapper(attractionId));
    }

    @Override
    public boolean have(String attractionId) {
        return attractionMapper.exists(attractionQueryWrapper(attractionId));
    }

    @Override
    public Attraction getAttraction(String attractionId) {
        return attractionMapper.selectOne(attractionQueryWrapper(attractionId));
    }

    @Override
    public Page<Attraction> attractionsNameLike(String name, long pageSize, long currentPage) {
        return attractionMapper.selectPage(new Page<>(currentPage, pageSize), nameLikeQueryWrapper(name));
    }

    @Override
    public Page<Attraction> getAttractionList(long pageSize, long currentPage) {
        return attractionMapper.selectPage(new Page<>(currentPage, pageSize), null);
    }

    @Override
    public Page<Attraction> blurSearch(String keyWords, long pageSize, long currentPage) {
        return null;
    }

    private QueryWrapper<Attraction> attractionQueryWrapper(String attractionId) {
        QueryWrapper<Attraction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        return queryWrapper;
    }

    private QueryWrapper<Attraction> nameLikeQueryWrapper(String name) {
        QueryWrapper<Attraction> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return queryWrapper;
    }
}
