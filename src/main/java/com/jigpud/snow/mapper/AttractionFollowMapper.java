package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.AttractionFollow;
import com.jigpud.snow.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface AttractionFollowMapper extends BaseMapper<AttractionFollow> {
    void insertIgnore(AttractionFollow attractionFollow);

    IPage<Attraction> getFollowingList(String userid, IPage<Attraction> page);

    IPage<User> getFollowerList(String attractionId, IPage<User> page);
}
