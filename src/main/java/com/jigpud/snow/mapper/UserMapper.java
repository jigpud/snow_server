package com.jigpud.snow.mapper;

import com.jigpud.snow.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface UserMapper {
    User getUserByUsername(String username);
    User getUserByUserid(String userid);
}
