package com.jigpud.snow.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jigpud.snow.util.typehandler.StringListTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : jigpud
 * 游记
 */
@TableName(value = "story", autoResultMap = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Story {
    // 作者的用户id
    private String authorId;

    // 图片
    @TableField(value = "pictures", typeHandler = StringListTypeHandler.class)
    private List<String> pictures;

    // 游记id
    private String storyId;

    // 发布的时间
    private Long releaseTime;

    // 标题
    private String title;

    // 内容
    private String content;

    // 所属的景点
    private String attractionId;

    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;
}
