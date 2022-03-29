package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class FollowController extends BaseController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    FollowController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.FOLLOW)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> follow(
            @RequestParam(value = FormDataConstant.USERID, required = false, defaultValue = "") String userid,
            HttpServletRequest request
    ) {
        String selfUserid = tokenService.getUserid(getToken(request));
        if (userService.haveUseridIs(userid)) {
            userService.follow(selfUserid, userid);
            if (userService.haveFollowed(selfUserid, userid)) {
                log.debug("follow success!");
                return Response.responseSuccess();
            } else {
                log.debug("follow failed!");
                return Response.responseFailed("关注失败！");
            }
        } else {
            log.debug("user not exists, userid: {}", userid);
            return Response.responseFailed("用户不存在！");
        }
    }
}
