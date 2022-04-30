package com.jigpud.snow.controller.attraction;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.token.TokenService;
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
public class UnfollowAttractionController extends BaseController {
    private final AttractionService attractionService;
    private final FollowService followService;
    private final TokenService tokenService;

    @Autowired
    UnfollowAttractionController(
            AttractionService attractionService,
            FollowService followService,
            TokenService tokenService
    ) {
        this.attractionService = attractionService;
        this.followService = followService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.UNFOLLOW_ATTRACTION)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> unfollowAttraction(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId,
            HttpServletRequest request
    ) {
        if (attractionService.haveAttraction(attractionId)) {
            String userid = tokenService.getUserid(getToken(request));
            followService.unfollowAttraction(attractionId, userid);
            if (!followService.haveFollowedAttraction(attractionId, userid)) {
                log.debug("unfollow attraction success!");
                return Response.responseSuccess();
            } else {
                log.debug("unfollow attraction failed!");
                return Response.responseFailed("取消关注景点失败！");
            }
        } else {
            log.debug("attraction {} not exists!", attractionId);
            return Response.responseFailed("景点不存在！");
        }
    }
}
