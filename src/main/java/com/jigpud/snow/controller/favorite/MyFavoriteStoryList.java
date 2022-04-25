package com.jigpud.snow.controller.favorite;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.StoryResponse;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.favorite.FavoriteService;
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
public class MyFavoriteStoryList extends BaseController {
    private final FavoriteService favoriteService;
    private final StoryService storyService;
    private final UserService userService;
    private final TokenService tokenService;
    private final LikeService likeService;
    private final AttractionService attractionService;

    @Autowired
    MyFavoriteStoryList(
            FavoriteService favoriteService,
            StoryService storyService,
            UserService userService,
            TokenService tokenService,
            LikeService likeService,
            AttractionService attractionService
    ) {
        this.favoriteService = favoriteService;
        this.storyService = storyService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.likeService = likeService;
        this.attractionService = attractionService;
    }

    @PostMapping(PathConstant.GET_SELF_FAVORITE_STORY_LIST)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<PageData<StoryResponse>> selfStoryFavoriteList(
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        log.debug("get self favorite story list with pageSize: {}", pageSize);
        log.debug("get self favorite story list with currentPage: {}", currentPage);
        String userid = tokenService.getUserid(getToken(request));
        PageData<Story> favoriteStoryList = PageData.fromPageData(
                favoriteService.storyFavoriteList(userid, pageSize, currentPage),
                storyService::getStory
        );
        PageData<StoryResponse> responsePageData = PageData.fromPageData(favoriteStoryList, story ->
                StoryResponse.create(story, userid, userService, likeService, attractionService, favoriteService));
        return Response.responseSuccess(responsePageData);
    }
}
