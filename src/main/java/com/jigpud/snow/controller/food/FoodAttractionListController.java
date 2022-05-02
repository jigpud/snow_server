package com.jigpud.snow.controller.food;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.response.AttractionResponse;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.food.FoodService;
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
public class FoodAttractionListController extends BaseController {
    private final FoodService foodService;
    private final TokenService tokenService;
    private final FollowService followService;
    private final StoryService storyService;
    private final AttractionService attractionService;

    @Autowired
    FoodAttractionListController(
            FoodService foodService,
            TokenService tokenService,
            FollowService followService,
            StoryService storyService,
            AttractionService attractionService
    ) {
        this.foodService = foodService;
        this.tokenService = tokenService;
        this.followService = followService;
        this.storyService = storyService;
        this.attractionService = attractionService;
    }

    @PostMapping(PathConstant.GET_FOOD_ATTRACTION_LIST)
    ResponseBody<PageData<AttractionResponse>> getFoodAttractionList(
            @RequestParam(value = FormDataConstant.FOOD_ID, required = false, defaultValue = "") String foodId,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        log.debug("get food attraction list with pageSize: {}", pageSize);
        log.debug("get food attraction list with currentPage: {}", currentPage);
        String userid = tokenService.getUserid(getToken(request));
        PageData<Attraction> attractionList = foodService.getAttractionList(foodId, pageSize, currentPage);
        PageData<AttractionResponse> attractionResponseList = PageData.fromPageData(attractionList, attraction ->
                AttractionResponse.create(attraction, userid, attractionService, followService, storyService));
        return Response.responseSuccess(attractionResponseList);
    }
}
