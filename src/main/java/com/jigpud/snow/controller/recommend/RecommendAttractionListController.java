package com.jigpud.snow.controller.recommend;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.response.AttractionResponse;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.recommend.RecommendService;
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
public class RecommendAttractionListController extends BaseController {
    private final RecommendService recommendService;
    private final TokenService tokenService;
    private final AttractionService attractionService;
    private final FollowService followService;
    private final StoryService storyService;

    @Autowired
    RecommendAttractionListController(
            RecommendService recommendService,
            TokenService tokenService,
            AttractionService attractionService,
            FollowService followService,
            StoryService storyService
    ) {
        this.recommendService = recommendService;
        this.tokenService = tokenService;
        this.attractionService = attractionService;
        this.followService = followService;
        this.storyService = storyService;
    }

    @PostMapping(PathConstant.GET_RECOMMEND_ATTRACTION_LIST)
    ResponseBody<PageData<AttractionResponse>> getRecommendAttractionList(
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        log.debug("get recommend attraction list with pageSize: {}", pageSize);
        log.debug("get recommend attraction list with currentPage: {}", currentPage);
        String userid = tokenService.getUserid(getToken(request));
        PageData<Attraction> recommendAttractionList = recommendService.getRecommendAttractionList(pageSize, currentPage);
        PageData<AttractionResponse> recommendAttractionResponseList = PageData.fromPageData(recommendAttractionList, attraction ->
                AttractionResponse.create(attraction, userid, attractionService, followService, storyService));
        log.debug("get recommend attraction list: {}", recommendAttractionResponseList);
        return Response.responseSuccess(recommendAttractionResponseList);
    }
}
