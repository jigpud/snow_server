package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.AttractionPicture;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface AttractionPictureMapper extends BaseMapper<AttractionPicture> {
    void insertIgnore(AttractionPicture attractionPicture);
}
