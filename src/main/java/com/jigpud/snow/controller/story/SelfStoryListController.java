package com.jigpud.snow.controller.story;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
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

    @Autowired
    SelfStoryListController(StoryService storyService, TokenService tokenService) {
        this.storyService = storyService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.GET_STORY_LIST)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_READ)
    ResponseBody<PageData<SelfStoryListResponse>> getStoryList(
            @RequestParam(value = FormDataConstant.PAGE_COUNT, required = false, defaultValue = "0") Long pageCount,
            @RequestParam(value = FormDataConstant.PAGE, required = false, defaultValue = "0") Long page,
            HttpServletRequest request
    ) {
        log.debug("get user story list with pageCount: {}", pageCount);
        log.debug("get user story list with page: {}", page);
        String token = getToken(request);
        String userid = tokenService.getUserid(token);
        PageData<Story> stories = storyService.getUserStoryList(userid, pageCount, page);
        PageData<SelfStoryListResponse> responsePageData = PageData.fromPageData(stories, story -> {
            String storyId = story.getStoryId();
            SelfStoryListResponse selfStoryListResponse = new SelfStoryListResponse();
            selfStoryListResponse.setStoryId(story.getStoryId());
            selfStoryListResponse.setAuthorId(story.getAuthorId());
            selfStoryListResponse.setTitle(story.getTitle());
            selfStoryListResponse.setContent(story.getContent());
            selfStoryListResponse.setPictures(story.getPictures());
            selfStoryListResponse.setReleaseTime(story.getReleaseTime());
            selfStoryListResponse.setReleaseLocation(story.getReleaseLocation());
            selfStoryListResponse.setAttractionId(story.getAttractionId());
            selfStoryListResponse.setLiked(storyService.haveLiked(storyId, userid));
            selfStoryListResponse.setLikes(storyService.likes(storyId));
            return selfStoryListResponse;
        });
        return Response.responseSuccess(responsePageData);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class SelfStoryListResponse {
        private String storyId;
        private String authorId;
        private String title;
        private String content;
        private Long likes;
        private List<String> pictures;
        private Long releaseTime;
        private String releaseLocation;
        private Boolean liked;
        private String attractionId;
    }
}
