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
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.PageData;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class SelfStoryListController extends BaseController {
    private final StoryService storyService;
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    SelfStoryListController(
            StoryService storyService,
            TokenService tokenService,
            UserService userService
    ) {
        this.storyService = storyService;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping(PathConstant.GET_SELF_STORY_LIST)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_READ)
    ResponseBody<PageData<StoryResponse>> getStoryList(
            @RequestParam(value = FormDataConstant.PAGE_COUNT, required = false, defaultValue = "0") Long pageCount,
            @RequestParam(value = FormDataConstant.PAGE, required = false, defaultValue = "0") Long page,
            HttpServletRequest request
    ) {
        log.debug("get user story list with pageCount: {}", pageCount);
        log.debug("get user story list with page: {}", page);
        String token = getToken(request);
        String selfUserid = tokenService.getUserid(token);
        PageData<Story> stories = storyService.getUserStoryList(selfUserid, pageCount, page);
        PageData<StoryResponse> responsePageData = PageData.fromPageData(stories, story -> {
            String storyId = story.getStoryId();
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
            storyResponse.setLiked(storyService.haveLiked(storyId, selfUserid));
            storyResponse.setLikes(storyService.likes(storyId));
            return storyResponse;
        });
        return Response.responseSuccess(responsePageData);
    }
}
