package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.SelfInformationResponse;
import com.jigpud.snow.service.favorite.FavoriteService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class SelfInformationController extends BaseController {
    private final UserService userService;
    private final TokenService tokenService;
    private final FavoriteService favoriteService;
    private final FollowService followService;

    @Autowired
    SelfInformationController(
            UserService userService,
            TokenService tokenService,
            FavoriteService favoriteService,
            FollowService followService
    ) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.favoriteService = favoriteService;
        this.followService = followService;
    }

    @GetMapping(PathConstant.GET_USER_INFORMATION)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_READ)
    ResponseBody<SelfInformationResponse> getSelfInformation(
            HttpServletRequest request
    ) {
        String token = getToken(request);
        String userid = tokenService.getUserid(token);
        User user = userService.getUserByUserid(userid);
        SelfInformationResponse info = new SelfInformationResponse();
        info.setUsername(user.getUsername());
        info.setUserid(user.getUserid());
        info.setAvatar(user.getAvatar());
        info.setNickname(user.getNickname());
        info.setGender(user.getGender());
        info.setAge(user.getAge());
        info.setSignature(user.getSignature());
        info.setFavorites(favoriteService.favorites(userid));
        info.setFollowers(followService.followerCount(userid));
        info.setFollowing(followService.followingCount(userid));
        info.setBackground(user.getBackground());
        log.debug("get self information success! info: {}", info);
        return Response.responseSuccess(info);
    }
}
