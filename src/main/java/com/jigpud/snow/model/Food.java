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
@TableName("food")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Food {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 美食
    private String foodId;

    // 名称
    private String name;

    // 简介
    private String description;
}
