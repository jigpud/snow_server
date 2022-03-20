package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jigpud
 */
@Slf4j
@RestController
public class UserInformationController extends BaseController {
    private final UserService userService;

    @Autowired
    UserInformationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(PathConstant.GET_USER_INFORMATION)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_READ)
    ResponseBody<UserInformationResponse> getUserInformation(
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username
    ) {
        if (!username.isEmpty() && userService.haveUsernameIs(username)) {
            User user = userService.getUserByUsername(username);
            UserInformationResponse info = UserInformationResponse.from(user);
            log.debug("get user information success! info: {}", info);
            return Response.responseSuccess(info);
        }
        log.debug("user not exists, username: {}", username);
        return Response.responseFailed("用户不存在！");
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class UserInformationResponse {
        private String username;
        private String nickname;
        private String gender;
        private Integer age;
        private String signature;
        private Long likes;

        public static UserInformationResponse from(User user) {
            UserInformationResponse info = new UserInformationResponse();
            info.setUsername(user.getUsername());
            info.setGender(user.getGender());
            info.setAge(user.getAge());
            info.setSignature(user.getSignature());
            info.setLikes(user.getLikes());
            return info;
        }
    }
}
