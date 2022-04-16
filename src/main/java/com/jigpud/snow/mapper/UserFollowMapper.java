package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.UserFollow;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface UserFollowMapper extends BaseMapper<UserFollow> {
    void insertIgnore(UserFollow userFollow);
}
