package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.AttractionFollow;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface AttractionFollowMapper extends BaseMapper<AttractionFollow> {
    void insertIgnore(AttractionFollow attractionFollow);
}
