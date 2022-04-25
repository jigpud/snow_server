package com.jigpud.snow.controller.story;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Story;
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
public class StoryDetailController extends BaseController {
    private final StoryService storyService;
    private final TokenService tokenService;
    private final UserService userService;
    private final LikeService likeService;
    private final AttractionService attractionService;
    private final FavoriteService favoriteService;

    @Autowired
    StoryDetailController(
            StoryService storyService,
            TokenService tokenService,
            UserService userService,
            LikeService likeService,
            AttractionService attractionService,
            FavoriteService favoriteService
    ) {
        this.storyService = storyService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.likeService = likeService;
        this.attractionService = attractionService;
        this.favoriteService = favoriteService;
    }

    @PostMapping(PathConstant.GET_STORY)
    ResponseBody<StoryResponse> getStory(
            @RequestParam(value = FormDataConstant.STORY_ID, required = false, defaultValue = "") String storyId,
            HttpServletRequest request
    ) {
        Story story = storyService.getStory(storyId);
        if (story != null) {
            String userid = tokenService.getUserid(getToken(request));
            StoryResponse storyResponse = StoryResponse.create(story, userid, userService, likeService,
                    attractionService, favoriteService);
            return Response.responseSuccess(storyResponse);
        } else {
            log.debug("story {} not exist!", storyId);
            return Response.responseFailed("游记不存在！");
        }
    }
}
