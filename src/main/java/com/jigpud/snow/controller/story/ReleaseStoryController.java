package com.jigpud.snow.controller.story;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.encrypt.Encryptor;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
            @RequestBody ReleaseStoryRequestBody releaseStoryRequestBody,
            HttpServletRequest request
    ) {
        if (verifyStory(releaseStoryRequestBody)) {
            String userid = tokenService.getUserid(getToken(request));
            String storyId = Encryptor.uuid();
            Story story = new Story();
            story.setStoryId(storyId);
            story.setAuthorId(userid);
            story.setAttractionId(releaseStoryRequestBody.getAttractionId());
            story.setTitle(releaseStoryRequestBody.getTitle());
            story.setContent(releaseStoryRequestBody.getContent());
            story.setPictures(releaseStoryRequestBody.getPictures());
            story.setReleaseTime(releaseStoryRequestBody.getReleaseTime());
            story.setReleaseLocation(releaseStoryRequestBody.getReleaseLocation());
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

    private boolean verifyStory(ReleaseStoryRequestBody releaseStoryRequestBody) {
        return releaseStoryRequestBody != null &&
                releaseStoryRequestBody.getTitle() != null && !releaseStoryRequestBody.getTitle().isEmpty() &&
                releaseStoryRequestBody.getContent() != null && !releaseStoryRequestBody.getContent().isEmpty() &&
                releaseStoryRequestBody.getReleaseTime() != null &&
                releaseStoryRequestBody.getReleaseLocation() != null &&
                releaseStoryRequestBody.getAttractionId() != null && !releaseStoryRequestBody.getAttractionId().isEmpty();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class ReleaseStoryRequestBody {
        private String title;
        private String content;
        private List<String> pictures;
        private String releaseLocation;
        private Long releaseTime;
        private String attractionId;
    }
}
