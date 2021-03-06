package com.jigpud.snow.controller.attraction;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.response.AttractionResponse;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.follow.FollowService;
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
public class AttractionDetailController extends BaseController {
    private final AttractionService attractionService;
    private final FollowService followService;
    private final TokenService tokenService;
    private final StoryService storyService;

    @Autowired
    AttractionDetailController(
            AttractionService attractionService,
            FollowService followService,
            TokenService tokenService,
            StoryService storyService
    ) {
        this.attractionService = attractionService;
        this.followService = followService;
        this.tokenService = tokenService;
        this.storyService = storyService;
    }

    @PostMapping(PathConstant.GET_ATTRACTION)
    ResponseBody<AttractionResponse> getAttraction(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId,
            HttpServletRequest request
    ) {
        if (attractionService.haveAttraction(attractionId)) {
            Attraction attraction = attractionService.getAttraction(attractionId);
            String userid = tokenService.getUserid(getToken(request));
            AttractionResponse attractionResponse = AttractionResponse.create(attraction, userid, attractionService,
                    followService, storyService);
            return Response.responseSuccess(attractionResponse);
        } else {
            log.debug("attraction {} not exists!", attractionId);
            return Response.responseFailed("??????????????????");
        }
    }
}
