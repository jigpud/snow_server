package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface SearchMapper {
    IPage<User> searchUser(String keyWords, IPage<User> page);

    IPage<Story> searchStory(String keyWords, IPage<Story> page);

    IPage<Attraction> searchAttraction(String keyWords, IPage<Attraction> page);
}
