package com.jigpud.snow.controller.story;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.model.User;
import com.jigpud.snow.response.StoryResponse;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
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

    @Autowired
    StoryDetailController(
            StoryService storyService,
            TokenService tokenService,
            UserService userService
    ) {
        this.storyService = storyService;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping(PathConstant.GET_STORY)
    ResponseBody<StoryResponse> getStory(
            @RequestParam(value = FormDataConstant.STORY_ID, required = false, defaultValue = "") String storyId,
            HttpServletRequest request
    ) {
        Story story = storyService.getStory(storyId);
        if (story != null) {
            String userid = tokenService.getUserid(getToken(request));
            User author = userService.getUserByUserid(story.getAuthorId());
            StoryResponse storyResponse = new StoryResponse();
            storyResponse.setStoryId(story.getStoryId());
            storyResponse.setAuthorId(author.getUserid());
            storyResponse.setAuthorNickname(author.getNickname());
            storyResponse.setAuthorAvatar(author.getAvatar());
            storyResponse.setTitle(story.getTitle());
            storyResponse.setContent(story.getContent());
            storyResponse.setPictures(story.getPictures());
            storyResponse.setReleaseTime(story.getReleaseTime());
            storyResponse.setReleaseLocation(story.getReleaseLocation());
            storyResponse.setAttractionId(story.getAttractionId());
            storyResponse.setLiked(storyService.haveLiked(storyId, userid));
            storyResponse.setLikes(storyService.likes(storyId));
            return Response.responseSuccess(storyResponse);
        } else {
            log.debug("story {} not exist!", storyId);
            return Response.responseFailed("游记不存在！");
        }
    }
}
