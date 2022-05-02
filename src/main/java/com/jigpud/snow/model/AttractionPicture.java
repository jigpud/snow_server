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
@TableName("attraction_picture")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttractionPicture {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 上传者
    private String uploaderId;

    // 景点
    private String attractionId;

    // 照片链接
    private String picture;

    // 照片链接MD5
    private String pictureMd5;
}
