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
public class SelfStoryListController extends BaseController {
    private final StoryService storyService;
    private final TokenService tokenService;
    private final UserService userService;
    private final LikeService likeService;

    @Autowired
    SelfStoryListController(
            StoryService storyService,
            TokenService tokenService,
            UserService userService,
            LikeService likeService
    ) {
        this.storyService = storyService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.likeService = likeService;
    }

    @PostMapping(PathConstant.GET_SELF_STORY_LIST)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_READ)
    ResponseBody<PageData<StoryResponse>> getStoryList(
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        log.debug("get user story list with pageSize: {}", pageSize);
        log.debug("get user story list with currentPage: {}", currentPage);
        String token = getToken(request);
        String userid = tokenService.getUserid(token);
        PageData<Story> stories = storyService.getUserStoryList(userid, pageSize, currentPage);
        PageData<StoryResponse> responsePageData = PageData.fromPageData(stories, story ->
                StoryResponse.create(story, userid, userService, likeService));
        return Response.responseSuccess(responsePageData);
    }
}
