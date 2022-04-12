package com.jigpud.snow.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : jigpud
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInformationResponse {
    private String userid;
    private String nickname;
    private String avatar;
    private String background;
    private String signature;
    private String gender;
    private Integer age;
    private Long likes;
    private Long followers;
    private Long followed;
    private Boolean haveFollowed;
}
