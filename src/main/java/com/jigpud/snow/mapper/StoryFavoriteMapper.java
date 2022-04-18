package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.StoryFavorite;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface StoryFavoriteMapper extends BaseMapper<StoryFavorite> {
    void insertIgnore(StoryFavorite storyFavorite);
}
