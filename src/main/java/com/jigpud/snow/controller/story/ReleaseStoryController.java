package com.jigpud.snow.controller.story;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.request.ReleaseStoryRequest;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.encrypt.Encryptor;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class ReleaseStoryController extends BaseController {
    private final TokenService tokenService;
    private final StoryService storyService;

    @Autowired
    ReleaseStoryController(TokenService tokenService, StoryService storyService) {
        this.tokenService = tokenService;
        this.storyService = storyService;
    }

    @PostMapping(PathConstant.RELEASE_STORY)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> releaseStory(
            @RequestBody ReleaseStoryRequest releaseStoryRequest,
            HttpServletRequest request
    ) {
        if (verifyStory(releaseStoryRequest)) {
            String userid = tokenService.getUserid(getToken(request));
            String storyId = Encryptor.uuid();
            Story story = new Story();
            story.setStoryId(storyId);
            story.setAuthorId(userid);
            story.setAttractionId(releaseStoryRequest.getAttractionId());
            story.setTitle(releaseStoryRequest.getTitle());
            story.setContent(releaseStoryRequest.getContent());
            story.setPictures(releaseStoryRequest.getPictures());
            story.setReleaseTime(releaseStoryRequest.getReleaseTime());
            storyService.releaseStory(story);
            if (storyService.getStory(storyId) != null) {
                log.debug("release story success!");
                return Response.responseSuccess();
            } else {
                log.debug("release story failed!");
                return Response.responseFailed("游记发布失败！");
            }
        } else {
            log.debug("story verification not passed!");
            return Response.responseFailed("游记信息不完整！");
        }
    }

    private boolean verifyStory(ReleaseStoryRequest releaseStoryRequest) {
        return releaseStoryRequest != null &&
                releaseStoryRequest.getTitle() != null && !releaseStoryRequest.getTitle().isEmpty() &&
                releaseStoryRequest.getContent() != null && !releaseStoryRequest.getContent().isEmpty() &&
                releaseStoryRequest.getReleaseTime() != null &&
                releaseStoryRequest.getAttractionId() != null && !releaseStoryRequest.getAttractionId().isEmpty();
    }
}
