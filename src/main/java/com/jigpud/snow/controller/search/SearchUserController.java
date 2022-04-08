package com.jigpud.snow.controller.search;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.PageData;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    ResponseBody<PageData<SearchUserResponse>> searchUser(
            @RequestParam(value = FormDataConstant.KEY_WORDS, required = false, defaultValue = "") String keyWords,
            @RequestParam(value = FormDataConstant.PAGE_COUNT, required = false, defaultValue = "0") Long pageCount,
            @RequestParam(value = FormDataConstant.PAGE, required = false, defaultValue = "0") Long page,
            HttpServletRequest request
    ) {
        if (!keyWords.isEmpty()) {
            String selfUserid = tokenService.getUserid(getToken(request));
            PageData<User> userPageData = userService.search(keyWords, pageCount, page);
            PageData<SearchUserResponse> searchUserResponsePageData = PageData.fromPageData(userPageData, user -> {
                String userid = user.getUserid();
                SearchUserResponse searchUserResponse = new SearchUserResponse();
                searchUserResponse.setUserid(userid);
                searchUserResponse.setNickname(user.getNickname());
                searchUserResponse.setBackground(user.getBackground());
                searchUserResponse.setGender(user.getGender());
                searchUserResponse.setAge(user.getAge());
                searchUserResponse.setSignature(user.getSignature());
                searchUserResponse.setLikes(userService.likes(userid));
                searchUserResponse.setFollowers(userService.followerCount(userid));
                searchUserResponse.setFollowed(userService.followedCount(userid));
                searchUserResponse.setHaveFollowed(userService.haveFollowed(selfUserid, userid));
                return searchUserResponse;
            });
            return Response.responseSuccess(searchUserResponsePageData);
        } else {
            log.debug("searchUser: keyWords is empty!");
            return Response.responseFailed("关键词不能为空！");
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class SearchUserResponse {
        private String userid;
        private String nickname;
        private String background;
        private String gender;
        private Integer age;
        private String signature;
        private Long likes;
        private Long followers;
        private Long followed;
        private Boolean haveFollowed;
    }
}
