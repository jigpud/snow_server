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
@TableName("attraction_tag")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttractionTag {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 景点
    private String attractionId;

    // 标签
    private String tag;

    // 标签MD5
    private String tagMd5;
}
