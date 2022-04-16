package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.Favorite;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface FavoriteMapper extends BaseMapper<Favorite> {
    void insertIgnore(Favorite favorite);
}
