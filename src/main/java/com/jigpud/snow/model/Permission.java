package com.jigpud.snow.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jigpud
 * 权限
 */
@TableName("permission")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Permission {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;

    // 用户
    private String userid;

    // 权限
    private String permission;
}
