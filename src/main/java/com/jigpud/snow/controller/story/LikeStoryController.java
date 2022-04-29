package com.jigpud.snow.controller.story;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.like.LikeService;
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
public class LikeStoryController extends BaseController {
    private final TokenService tokenService;
    private final StoryService storyService;
    private final LikeService likeService;

    @Autowired
    LikeStoryController(
            TokenService tokenService,
            StoryService storyService,
            LikeService likeService
    ) {
        this.tokenService = tokenService;
        this.storyService = storyService;
        this.likeService = likeService;
    }

    @PostMapping(PathConstant.LIKE_STORY)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> likeStory(
            @RequestParam(value = FormDataConstant.STORY_ID, required = false, defaultValue = "") String storyId,
            HttpServletRequest request
    ) {
        String userid = tokenService.getUserid(getToken(request));
        if (storyService.getStory(storyId) != null) {
            likeService.likeStory(storyId, userid);
            if (likeService.haveLikedStory(storyId, userid)) {
                log.debug("like story success!");
                return Response.responseSuccess();
            } else {
                log.debug("like story failed!");
                return Response.responseFailed("点赞失败！");
            }
        } else {
            log.debug("story not exists, story id: {}", storyId);
            return Response.responseFailed("游记不存在！");
        }
    }
}
