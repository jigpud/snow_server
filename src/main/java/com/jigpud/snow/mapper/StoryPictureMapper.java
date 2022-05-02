package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.StoryPicture;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface StoryPictureMapper extends BaseMapper<StoryPicture> {
    void insertIgnore(StoryPicture storyPicture);
}
