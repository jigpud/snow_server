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
@TableName("user_follow")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserFollow {
    // 自己
    private String userid;

    // 关注者
    private String followerId;

    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;
}
