package com.jigpud.snow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jigpud
 * 用户
 */
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
    // 获赞
    private long likes;
    // 主键
    private long id;
}
