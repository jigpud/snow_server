package com.jigpud.snow.controller.story;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.request.PostStoryRequest;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.Response;
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
public class PostStoryController extends BaseController {
    private final TokenService tokenService;
    private final StoryService storyService;

    @Autowired
    PostStoryController(TokenService tokenService, StoryService storyService) {
        this.tokenService = tokenService;
        this.storyService = storyService;
    }

    @PostMapping(PathConstant.POST_STORY)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> postStory(
            @RequestBody PostStoryRequest postStoryRequest,
            HttpServletRequest request
    ) {
        if (verifyStory(postStoryRequest)) {
            String title = postStoryRequest.getTitle();
            String content = postStoryRequest.getContent();
            List<String> pictures = postStoryRequest.getPictures();
            String authorId = tokenService.getUserid(getToken(request));
            String attractionId = postStoryRequest.getAttractionId();
            String storyId = storyService.postStory(title, content, authorId, attractionId);
            if (storyService.getStory(storyId) != null) {
                pictures.forEach(picture -> storyService.addPicture(storyId, authorId, picture));
                log.debug("post story success!");
                return Response.responseSuccess();
            } else {
                log.debug("post story failed!");
                return Response.responseFailed("游记发布失败！");
            }
        } else {
            log.debug("story verification not passed!");
            return Response.responseFailed("游记信息不完整！");
        }
    }

    private boolean verifyStory(PostStoryRequest postStoryRequest) {
        return postStoryRequest != null &&
                postStoryRequest.getTitle() != null && !postStoryRequest.getTitle().isEmpty() &&
                postStoryRequest.getContent() != null && !postStoryRequest.getContent().isEmpty() &&
                postStoryRequest.getAttractionId() != null && !postStoryRequest.getAttractionId().isEmpty();
    }
}
