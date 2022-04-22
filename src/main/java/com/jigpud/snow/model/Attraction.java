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
 */
@TableName(value = "attraction", autoResultMap = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attraction {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 景点id
    private String attractionId;

    // 名称
    private String name;

    // 简介
    private String description;

    // 标签
    @TableField(value = "tags", typeHandler = StringListTypeHandler.class)
    private List<String> tags;

    // 景点的位置
    private String location;
}
