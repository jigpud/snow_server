package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.AttractionPhoto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface AttractionPhotoMapper extends BaseMapper<AttractionPhoto> {
    void insertIgnore(AttractionPhoto attractionPhoto);
}
