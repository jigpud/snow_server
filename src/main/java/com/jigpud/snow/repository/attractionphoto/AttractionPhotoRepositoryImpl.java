package com.jigpud.snow.repository.attractionphoto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.AttractionPhotoMapper;
import com.jigpud.snow.model.AttractionPhoto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class AttractionPhotoRepositoryImpl implements AttractionPhotoRepository {
    private final AttractionPhotoMapper attractionPhotoMapper;

    @Autowired
    AttractionPhotoRepositoryImpl(AttractionPhotoMapper attractionPhotoMapper) {
        this.attractionPhotoMapper = attractionPhotoMapper;
    }

    @Override
    public void add(AttractionPhoto attractionPhoto) {
        attractionPhotoMapper.insertIgnore(attractionPhoto);
    }

    @Override
    public void remove(String attractionId, String uploaderId, String photo) {
        attractionPhotoMapper.delete(attractionUploaderAndPhotoQueryWrapper(attractionId, uploaderId, photo));
    }

    @Override
    public void remove(String attractionId, String photo) {
        attractionPhotoMapper.delete(attractionAndPhotoQueryWrapper(attractionId, photo));
    }

    @Override
    public Page<AttractionPhoto> getAttractionPhotoList(String attractionId, long pageSize, long currentPage) {
        return attractionPhotoMapper.selectPage(new Page<>(currentPage, pageSize), attractionQueryWrapper(attractionId));
    }

    @Override
    public boolean have(String attractionId, String photo) {
        return attractionPhotoMapper.exists(attractionAndPhotoQueryWrapper(attractionId, photo));
    }

    private QueryWrapper<AttractionPhoto> attractionUploaderAndPhotoQueryWrapper(String attractionId, String uploaderId, String photo) {
        QueryWrapper<AttractionPhoto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uploader_id", uploaderId);
        queryWrapper.eq("attraction_id", attractionId);
        queryWrapper.eq("photo", photo);
        return queryWrapper;
    }

    private QueryWrapper<AttractionPhoto> attractionAndPhotoQueryWrapper(String attractionId, String photo) {
        QueryWrapper<AttractionPhoto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        queryWrapper.eq("photo", photo);
        return queryWrapper;
    }

    private QueryWrapper<AttractionPhoto> attractionQueryWrapper(String attractionId) {
        QueryWrapper<AttractionPhoto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        return queryWrapper;
    }
}
