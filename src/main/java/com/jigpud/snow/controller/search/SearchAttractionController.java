package com.jigpud.snow.controller.search;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.response.AttractionResponse;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.StoryResponse;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.follow.FollowService;
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
public class SearchAttractionController extends BaseController {
    private final SearchService searchService;
    private final TokenService tokenService;
    private final FollowService followService;
    private final StoryService storyService;
    private final AttractionService attractionService;

    @Autowired
    SearchAttractionController(
            SearchService searchService,
            TokenService tokenService,
            FollowService followService,
            StoryService storyService,
            AttractionService attractionService
    ) {
        this.searchService = searchService;
        this.tokenService = tokenService;
        this.followService = followService;
        this.storyService = storyService;
        this.attractionService = attractionService;
    }

    @PostMapping(PathConstant.SEARCH_ATTRACTION)
    ResponseBody<PageData<AttractionResponse>> searchAttraction(
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
            PageData<Attraction> attractionList = searchService.searchAttraction(keyWords, pageSize, currentPage);
            PageData<AttractionResponse> attractionResponseList = PageData.fromPageData(attractionList, attraction ->
                    AttractionResponse.create(attraction, userid, attractionService, followService, storyService));
            return Response.responseSuccess(attractionResponseList);
        } else {
            log.debug("searchStory: keyWords is empty!");
            return Response.responseFailed("关键词不能为空！");
        }
    }
}
