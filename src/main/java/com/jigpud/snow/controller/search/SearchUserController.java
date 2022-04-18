package com.jigpud.snow.controller.search;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.UserInformationResponse;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.like.LikeService;
import com.jigpud.snow.service.search.SearchService;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.Response;
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
    private final TokenService tokenService;
    private final SearchService searchService;
    private final FollowService followService;
    private final LikeService likeService;
    private final StoryService storyService;

    @Autowired
    SearchUserController(
            TokenService tokenService,
            SearchService searchService,
            FollowService followService,
            LikeService likeService,
            StoryService storyService
    ) {
        this.tokenService = tokenService;
        this.searchService = searchService;
        this.followService = followService;
        this.likeService = likeService;
        this.storyService = storyService;
    }

    @PostMapping(PathConstant.SEARCH_USER)
    ResponseBody<PageData<UserInformationResponse>> searchUser(
            @RequestParam(value = FormDataConstant.KEY_WORDS, required = false, defaultValue = "") String keyWords,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        if (!keyWords.isEmpty()) {
            String selfUserid = tokenService.getUserid(getToken(request));
            PageData<User> userPageData = searchService.searchUser(keyWords, pageSize, currentPage);
            PageData<UserInformationResponse> searchUserResponsePageData = PageData.fromPageData(userPageData, user ->
                    UserInformationResponse.create(user, selfUserid, followService, likeService, storyService));
            return Response.responseSuccess(searchUserResponsePageData);
        } else {
            log.debug("searchUser: keyWords is empty!");
            return Response.responseFailed("关键词不能为空！");
        }
    }
}
