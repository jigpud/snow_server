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
@TableName("story_likes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoryLikes {
    // 游记id
    private String storyId;

    // 用户id
    private String userid;

    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;
}
