package com.jigpud.snow.controller.story;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.StoryResponse;
import com.jigpud.snow.service.like.LikeService;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
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
public class UserStoryListController extends BaseController {
    private final StoryService storyService;
    private final LikeService likeService;
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    UserStoryListController(
            StoryService storyService,
            LikeService likeService,
            TokenService tokenService,
            UserService userService
    ) {
        this.storyService = storyService;
        this.likeService = likeService;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping(PathConstant.GET_USER_STORY_LIST)
    ResponseBody<PageData<StoryResponse>> getUserStoryList(
            @RequestParam(value = FormDataConstant.USERID, required = false, defaultValue = "") String userid,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        log.debug("get user story list with pageSize: {}", pageSize);
        log.debug("get user story list with currentPage: {}", currentPage);
        String selfUserid = tokenService.getUserid(getToken(request));
        PageData<Story> storyList = storyService.getUserStoryList(userid, pageSize, currentPage);
        PageData<StoryResponse> storyResponseList = PageData.fromPageData(storyList, story ->
                StoryResponse.create(story, selfUserid, userService, likeService));
        return Response.responseSuccess(storyResponseList);
    }
}
