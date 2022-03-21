package com.jigpud.snow.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jigpud
 * 用户
 */
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    // 手机号 + salt => hmac
    private String username;

    // 昵称
    private String nickname;

    // 密码 + salt => hmac
    private String password;

    // 个性签名
    private String signature;

    // salt
    private String salt;

    // 头像地址
    private String avatar;

    // 唯一用户id
    private String userid;

    // 性别
    private String gender;

    // 年龄
    private Integer age;

    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;
}
