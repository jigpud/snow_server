package com.jigpud.snow.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserRequest {
    private String userid;
    private String password;
    private String nickname;
    private String avatar;
    private String signature;
    private String gender;
    private Integer age;
    private String background;
}
