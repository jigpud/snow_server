package com.jigpud.snow.controller.attraction.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.admin.AttractionResponse;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.food.FoodService;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
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
public class QueryAttractionController extends BaseController {
    private final AttractionService attractionService;
    private final FollowService followService;
    private final StoryService storyService;
    private final FoodService foodService;

    @Autowired
    QueryAttractionController(
            AttractionService attractionService,
            FollowService followService,
            StoryService storyService,
            FoodService foodService
    ) {
        this.attractionService = attractionService;
        this.followService = followService;
        this.storyService = storyService;
        this.foodService = foodService;
    }

    @PostMapping(PathConstant.QUERY_ATTRACTION)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_READ)
    ResponseBody<PageData<AttractionResponse>> queryAttraction(
            @RequestParam(value = FormDataConstant.NAME, required = false, defaultValue = "") String name,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage
    ) {
        log.debug("ADMIN : get attraction list with pageSize: {}", pageSize);
        log.debug("ADMIN : get attraction list with currentPage: {}", currentPage);
        PageData<Attraction> attractionList = attractionService.attractionsNameLike(name, pageSize, currentPage);
        PageData<AttractionResponse> attractionResponseList = PageData.fromPageData(attractionList, attraction ->
                AttractionResponse.create(attraction, attractionService, followService, storyService, foodService));
        return Response.responseSuccess(attractionResponseList);
    }
}
