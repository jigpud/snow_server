package com.jigpud.snow.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface AdminMapper {
    int isAdmin(String userid);
}
