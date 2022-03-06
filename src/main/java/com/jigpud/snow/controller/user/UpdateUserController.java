package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.encrypt.Encryptor;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : jigpud
 * 更新用户
 */
@Slf4j
@RestController
public class UpdateUserController extends BaseController {
    private final UserService userService;

    @Autowired
    UpdateUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(PathConstant.UPDATE_USER)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> updateUser(@RequestBody UpdateUserRequest update) {
        log.debug("updateUser: {}", update);
        if (update != null && update.getUsername() != null && !update.getUsername().isEmpty()) {
            String username = update.getUsername();
            User user = userService.getUserByUsername(username);
            if (user != null && username.equals(user.getUsername())) {
                // 用户存在

                // update password
                if (update.getPassword() != null) {
                    String salt = user.getSalt();
                    user.setPassword(Encryptor.hmacSHA256Encrypt(update.getPassword(), salt));
                }

                // update nickname
                if (update.getNickname() != null) {
                    user.setNickname(update.getNickname());
                } else {
                    user.setNickname("");
                }

                // update avatar
                if (update.getAvatar() != null) {
                    user.setAvatar(update.getAvatar());
                } else {
                    user.setAvatar("");
                }

                // update signature
                if (update.getSignature() != null) {
                    user.setSignature(update.getSignature());
                } else {
                    user.setSignature("");
                }

                // update gender
                if (update.getGender() != null) {
                    user.setGender(update.getGender());
                } else {
                    user.setGender("");
                }

                // update age
                if (update.getAge() != null) {
                    user.setAge(update.getAge());
                } else {
                    user.setAge(0);
                }

                // update likes
                if (update.getLikes() != null) {
                    user.setLikes(update.getLikes());
                } else {
                    user.setLikes(0L);
                }

                // 更新用户信息
                log.debug("update user information success!");
                userService.update(user);
                return Response.responseSuccess();
            }
        }
        log.debug("ADMIN : update user failed!");
        return Response.responseFailed("更新用户信息失败！");
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class UpdateUserRequest {
        private String username;
        private String password;
        private String nickname;
        private String avatar;
        private String signature;
        private String gender;
        private Integer age;
        private Long likes;
    }
}
