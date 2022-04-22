package com.jigpud.snow.repository.attractionphoto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.AttractionPhotoMapper;
import com.jigpud.snow.model.AttractionPhoto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void remove(String uploaderId, String attractionId, String photo) {
        attractionPhotoMapper.delete(uploaderAttractionAndPhotoQueryWrapper(uploaderId, attractionId, photo));
    }

    @Override
    public List<AttractionPhoto> getAttractionPhotoList(String attractionId) {
        return attractionPhotoMapper.selectList(attractionQueryWrapper(attractionId));
    }

    @Override
    public boolean have(String attractionId, String photo) {
        return attractionPhotoMapper.exists(attractionAndPhotoQueryWrapper(attractionId, photo));
    }

    private QueryWrapper<AttractionPhoto> uploaderAttractionAndPhotoQueryWrapper(String uploaderId, String attractionId, String photo) {
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
