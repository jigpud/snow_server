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
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userid;
    private String storyId;
}
