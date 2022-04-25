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

    @Autowired
    SearchStoryController(
            SearchService searchService,
            TokenService tokenService,
            UserService userService,
            LikeService likeService,
            AttractionService attractionService,
            FavoriteService favoriteService
    ) {
        this.searchService = searchService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.likeService = likeService;
        this.attractionService = attractionService;
        this.favoriteService = favoriteService;
    }

    @PostMapping(PathConstant.SEARCH_STORY)
    ResponseBody<PageData<StoryResponse>> searchStory(
            @RequestParam(value = FormDataConstant.KEY_WORDS, required = false, defaultValue = "") String keyWords,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        if (!keyWords.isEmpty()) {
            String selfUserid = tokenService.getUserid(getToken(request));
            PageData<Story> storyPageData = searchService.searchStory(keyWords, pageSize, currentPage);
            PageData<StoryResponse> searchStoryResponsePageData = PageData.fromPageData(storyPageData, story ->
                    StoryResponse.create(story, selfUserid, userService, likeService, attractionService, favoriteService));
            return Response.responseSuccess(searchStoryResponsePageData);
        } else {
            log.debug("searchStory: keyWords is empty!");
            return Response.responseFailed("关键词不能为空！");
        }
    }
}
