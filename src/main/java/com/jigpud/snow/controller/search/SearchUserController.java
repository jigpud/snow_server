package com.jigpud.snow.controller.search;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.response.UserInformationResponse;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.PageData;
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
public class SearchUserController extends BaseController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    SearchUserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.SEARCH_USER)
    ResponseBody<PageData<UserInformationResponse>> searchUser(
            @RequestParam(value = FormDataConstant.KEY_WORDS, required = false, defaultValue = "") String keyWords,
            @RequestParam(value = FormDataConstant.PAGE_COUNT, required = false, defaultValue = "0") Long pageCount,
            @RequestParam(value = FormDataConstant.PAGE, required = false, defaultValue = "0") Long page,
            HttpServletRequest request
    ) {
        if (!keyWords.isEmpty()) {
            String selfUserid = tokenService.getUserid(getToken(request));
            PageData<User> userPageData = userService.search(keyWords, pageCount, page);
            PageData<UserInformationResponse> searchUserResponsePageData = PageData.fromPageData(userPageData, user -> {
                String userid = user.getUserid();
                UserInformationResponse userInformationResponse = new UserInformationResponse();
                userInformationResponse.setUserid(userid);
                userInformationResponse.setNickname(user.getNickname());
                userInformationResponse.setAvatar(user.getAvatar());
                userInformationResponse.setBackground(user.getBackground());
                userInformationResponse.setGender(user.getGender());
                userInformationResponse.setAge(user.getAge());
                userInformationResponse.setSignature(user.getSignature());
                userInformationResponse.setLikes(userService.likes(userid));
                userInformationResponse.setFollowers(userService.followerCount(userid));
                userInformationResponse.setFollowed(userService.followedCount(userid));
                userInformationResponse.setHaveFollowed(userService.haveFollowed(selfUserid, userid));
                return userInformationResponse;
            });
            return Response.responseSuccess(searchUserResponsePageData);
        } else {
            log.debug("searchUser: keyWords is empty!");
            return Response.responseFailed("关键词不能为空！");
        }
    }
}
