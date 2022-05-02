package com.jigpud.snow.repository.attractionpicture;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.AttractionPictureMapper;
import com.jigpud.snow.model.AttractionPicture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class AttractionPictureRepositoryImpl implements AttractionPictureRepository {
    private final AttractionPictureMapper attractionPictureMapper;

    @Autowired
    AttractionPictureRepositoryImpl(AttractionPictureMapper attractionPictureMapper) {
        this.attractionPictureMapper = attractionPictureMapper;
    }

    @Override
    public void add(AttractionPicture attractionPicture) {
        attractionPictureMapper.insertIgnore(attractionPicture);
    }

    @Override
    public void remove(String attractionId, String uploaderId, String picture) {
        attractionPictureMapper.delete(attractionUploaderAndPictureQueryWrapper(attractionId, uploaderId, picture));
    }

    @Override
    public void remove(String attractionId, String picture) {
        attractionPictureMapper.delete(attractionAndPictureQueryWrapper(attractionId, picture));
    }

    @Override
    public Page<AttractionPicture> getPictureList(String attractionId, long pageSize, long currentPage) {
        return attractionPictureMapper.selectPage(new Page<>(currentPage, pageSize), attractionQueryWrapper(attractionId));
    }

    @Override
    public boolean have(String attractionId, String picture) {
        return attractionPictureMapper.exists(attractionAndPictureQueryWrapper(attractionId, picture));
    }

    private QueryWrapper<AttractionPicture> attractionUploaderAndPictureQueryWrapper(String attractionId, String uploaderId, String picture) {
        QueryWrapper<AttractionPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uploader_id", uploaderId);
        queryWrapper.eq("attraction_id", attractionId);
        queryWrapper.eq("picture", picture);
        return queryWrapper;
    }

    private QueryWrapper<AttractionPicture> attractionAndPictureQueryWrapper(String attractionId, String picture) {
        QueryWrapper<AttractionPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        queryWrapper.eq("picture", picture);
        return queryWrapper;
    }

    private QueryWrapper<AttractionPicture> attractionQueryWrapper(String attractionId) {
        QueryWrapper<AttractionPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        return queryWrapper;
    }
}
