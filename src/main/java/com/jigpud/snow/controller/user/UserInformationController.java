package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.UserInformationResponse;
import com.jigpud.snow.model.User;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.extern.slf4j.Slf4j;
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
public class UserInformationController extends BaseController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    UserInformationController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.GET_USER_INFORMATION)
    ResponseBody<UserInformationResponse> getUserInformation(
            @RequestParam(value = FormDataConstant.USERID, required = false, defaultValue = "") String userid,
            HttpServletRequest request
    ) {
        String selfUserid = tokenService.getUserid(getToken(request));
        if (userService.haveUseridIs(userid)) {
            User user = userService.getUserByUserid(userid);
            UserInformationResponse info = new UserInformationResponse();
            info.setUserid(user.getUserid());
            info.setNickname(user.getNickname());
            info.setBackground(user.getBackground());
            info.setGender(user.getGender());
            info.setAge(user.getAge());
            info.setSignature(user.getSignature());
            info.setLikes(userService.likes(userid));
            info.setFollowers(userService.followerCount(userid));
            info.setFollowed(userService.followedCount(userid));
            info.setHaveFollowed(userService.haveFollowed(selfUserid, userid));
            log.debug("get user information success! info: {}", info);
            return Response.responseSuccess(info);
        }
        log.debug("user not exists, userid: {}", userid);
        return Response.responseFailed("用户不存在！");
    }
}
