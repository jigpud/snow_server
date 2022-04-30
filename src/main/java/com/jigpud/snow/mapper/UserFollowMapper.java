package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jigpud.snow.model.User;
import com.jigpud.snow.model.UserFollow;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface UserFollowMapper extends BaseMapper<UserFollow> {
    void insertIgnore(UserFollow userFollow);

    IPage<User> getFollowingList(String userid, IPage<User> page);

    IPage<User> getFollowerList(String userid, IPage<User> page);
}
