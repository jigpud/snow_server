package com.jigpud.snow.response;

import com.jigpud.snow.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jigpud
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private String username;
    private String userid;
    private String nickname;
    private String avatar;
    private String background;
    private String signature;
    private String gender;
    private Integer age;

    public static UserResponse create(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setUserid(user.getUserid());
        userResponse.setNickname(user.getNickname());
        userResponse.setBackground(user.getBackground());
        userResponse.setAvatar(user.getAvatar());
        userResponse.setGender(user.getGender());
        userResponse.setAge(user.getAge());
        userResponse.setSignature(user.getSignature());
        return userResponse;
    }
}
