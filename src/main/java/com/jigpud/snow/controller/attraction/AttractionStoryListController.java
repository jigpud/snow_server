package com.jigpud.snow.controller.attraction;

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
public class AttractionStoryListController extends BaseController {
    private final AttractionService attractionService;
    private final StoryService storyService;
    private final TokenService tokenService;
    private final UserService userService;
    private final LikeService likeService;
    private final FavoriteService favoriteService;

    @Autowired
    AttractionStoryListController(
            AttractionService attractionService,
            StoryService storyService,
            TokenService tokenService,
            UserService userService,
            LikeService likeService,
            FavoriteService favoriteService
    ) {
        this.attractionService = attractionService;
        this.storyService = storyService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.likeService = likeService;
        this.favoriteService = favoriteService;
    }

    @PostMapping(PathConstant.GET_ATTRACTION_STORY_LIST)
    ResponseBody<PageData<StoryResponse>> getAttractionStoryList(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        if (attractionService.haveAttraction(attractionId)) {
            log.debug("get attraction story list with pageSize: {}", pageSize);
            log.debug("get attraction story list with currentPage: {}", currentPage);
            String userid = tokenService.getUserid(getToken(request));
            PageData<Story> storyList = storyService.getAttractionStoryList(attractionId, pageSize, currentPage);
            PageData<StoryResponse> storyResponseList = PageData.fromPageData(storyList, story ->
                    StoryResponse.create(story, userid, storyService, userService, likeService, attractionService, favoriteService));
            return Response.responseSuccess(storyResponseList);
        } else {
            log.debug("attraction {} not exists!", attractionId);
            return Response.responseFailed("景点不存在！");
        }
    }
}
