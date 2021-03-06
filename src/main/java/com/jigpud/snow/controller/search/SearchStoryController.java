package com.jigpud.snow.controller.search;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.StoryResponse;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.favorite.FavoriteService;
import com.jigpud.snow.service.like.LikeService;
import com.jigpud.snow.service.search.SearchService;
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
public class SearchStoryController extends BaseController {
    private final SearchService searchService;
    private final TokenService tokenService;
    private final UserService userService;
    private final LikeService likeService;
    private final AttractionService attractionService;
    private final FavoriteService favoriteService;
    private final StoryService storyService;

    @Autowired
    SearchStoryController(
            SearchService searchService,
            TokenService tokenService,
            UserService userService,
            LikeService likeService,
            AttractionService attractionService,
            FavoriteService favoriteService,
            StoryService storyService
    ) {
        this.searchService = searchService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.likeService = likeService;
        this.attractionService = attractionService;
        this.favoriteService = favoriteService;
        this.storyService = storyService;
    }

    @PostMapping(PathConstant.SEARCH_STORY)
    ResponseBody<PageData<StoryResponse>> searchStory(
            @RequestParam(value = FormDataConstant.KEY_WORDS, required = false, defaultValue = "") String keyWords,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        log.debug("search with keyWords: {}", keyWords);
        log.debug("search user with pageSize: {}", pageSize);
        log.debug("search user with currentPage: {}", currentPage);
        if (!keyWords.isEmpty()) {
            String userid = tokenService.getUserid(getToken(request));
            PageData<Story> storyList = searchService.searchStory(keyWords, pageSize, currentPage);
            PageData<StoryResponse> storyResponseList = PageData.fromPageData(storyList, story ->
                    StoryResponse.create(story, userid, storyService, userService, likeService, attractionService, favoriteService));
            return Response.responseSuccess(storyResponseList);
        } else {
            log.debug("searchStory: keyWords is empty!");
            return Response.responseFailed("????????????????????????");
        }
    }
}
