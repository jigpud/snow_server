package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jigpud.snow.model.Story;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface StoryMapper extends BaseMapper<Story> {
    IPage<Story> getAttractionStoryList(String attractionId, IPage<Story> page);

    IPage<Story> getMomentsStoryList(String userid, IPage<Story> page);
}
