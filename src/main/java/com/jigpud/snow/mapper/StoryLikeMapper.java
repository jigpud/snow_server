package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.StoryLike;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface StoryLikeMapper extends BaseMapper<StoryLike> {
    void insertIgnore(StoryLike storyLikes);
}
