package com.jigpud.snow.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jigpud
 */
@TableName("story_favorite")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoryFavorite {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 用户
    private String userid;

    // 游记
    private String storyId;
}
