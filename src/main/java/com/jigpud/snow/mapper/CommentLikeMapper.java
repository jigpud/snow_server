package com.jigpud.snow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jigpud.snow.model.CommentLike;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : jigpud
 */
@Mapper
public interface CommentLikeMapper extends BaseMapper<CommentLike> {
    void insertIgnore(CommentLike commentLikes);
}
