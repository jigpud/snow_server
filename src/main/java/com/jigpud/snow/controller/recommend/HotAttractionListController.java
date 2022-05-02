package com.jigpud.snow.controller.recommend;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.response.AttractionResponse;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.recommend.RecommendService;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class HotAttractionListController extends BaseController {
    private final RecommendService recommendService;
    private final TokenService tokenService;
    private final AttractionService attractionService;
    private final FollowService followService;
    private final StoryService storyService;

    @Autowired
    HotAttractionListController(
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

    @GetMapping(PathConstant.GET_HOT_ATTRACTION_LIST)
    ResponseBody<List<AttractionResponse>> getHotAttractionList(
            HttpServletRequest request
    ) {
        String userid = tokenService.getUserid(getToken(request));
        List<Attraction> hotAttractionList = recommendService.getHotAttractionList(5, 1).getRecords();
        List<AttractionResponse> hotAttractionResponseList = hotAttractionList.stream()
                .map(attraction ->
                        AttractionResponse.create(attraction, userid, attractionService, followService, storyService))
                .collect(Collectors.toList());
        log.debug("get hot attraction list: {}", hotAttractionResponseList);
        return Response.responseSuccess(hotAttractionResponseList);
    }
}
