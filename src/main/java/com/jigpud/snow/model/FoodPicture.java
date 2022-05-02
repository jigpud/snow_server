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
@TableName("food_picture")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodPicture {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 美食
    private String foodId;

    // 照片链接
    private String picture;

    // 照片链接MD5
    private String pictureMd5;

    // 上传者
    private String uploaderId;
}
