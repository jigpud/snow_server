package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.Response;
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
public class UnfollowController extends BaseController {
    private final UserService userService;
    private final TokenService tokenService;
    private final FollowService followService;

    @Autowired
    UnfollowController(
            UserService userService,
            TokenService tokenService,
            FollowService followService
    ) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.followService = followService;
    }

    @PostMapping(PathConstant.UNFOLLOW)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> unfollow(
            @RequestParam(value = FormDataConstant.USERID, required = false, defaultValue = "") String userid,
            HttpServletRequest request
    ) {
        String selfUserid = tokenService.getUserid(getToken(request));
        if (userService.haveUseridIs(userid)) {
            followService.unfollowUser(selfUserid, userid);
            if (!followService.haveFollowedUser(selfUserid, userid)) {
                log.debug("unfollow success!");
                return Response.responseSuccess();
            } else {
                log.debug("unfollow failed!");
                return Response.responseFailed("?????????????????????");
            }
        } else {
            log.debug("user not exists, userid: {}", userid);
            return Response.responseFailed("??????????????????");
        }
    }
}
