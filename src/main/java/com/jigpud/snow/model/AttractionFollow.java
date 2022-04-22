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
@TableName("attraction_follow")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttractionFollow {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 用户
    private String userid;

    // 景点
    private String attractionId;
}
