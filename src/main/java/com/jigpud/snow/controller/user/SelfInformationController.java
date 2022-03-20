package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jigpud
 */
@Slf4j
@RestController
public class SelfInformationController extends BaseController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    SelfInformationController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping(PathConstant.GET_USER_INFORMATION)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_READ)
    ResponseBody<?> getSelfInformation(HttpServletRequest request) {
        String token = getToken(request);
        String userid = tokenService.getUserid(token);
        User user = userService.getUserByUserid(userid);
        SelfInformationResponse info = SelfInformationResponse.from(user);
        log.debug("get self information success! info: {}", info);
        return Response.responseSuccess(info);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class SelfInformationResponse {
        private String avatar;
        private String username;
        private String nickname;
        private String gender;
        private Integer age;
        private String signature;
        private Long likes;

        public static SelfInformationResponse from(User user) {
            SelfInformationResponse info = new SelfInformationResponse();
            info.setUsername(user.getUsername());
            info.setAvatar(user.getAvatar());
            info.setNickname(user.getNickname());
            info.setGender(user.getGender());
            info.setAge(user.getAge());
            info.setSignature(user.getSignature());
            info.setLikes(user.getLikes());
            return info;
        }
    }
}