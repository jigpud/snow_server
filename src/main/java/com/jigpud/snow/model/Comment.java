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
@TableName("comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 用户id
    private String authorId;

    // 评论id
    private String commentId;

    // 所属的游记
    private String storyId;

    // 回复的评论
    private String pid;

    // 评论内容
    private String content;
}
