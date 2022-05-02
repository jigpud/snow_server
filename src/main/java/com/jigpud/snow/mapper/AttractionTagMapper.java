package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.AttractionTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface AttractionTagMapper extends BaseMapper<AttractionTag> {
    void insertIgnore(AttractionTag attractionTag);
}
