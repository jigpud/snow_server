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
@TableName("comment_likes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentLikes {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 用户id
    private String userid;

    // 评论id
    private String commentId;
}
