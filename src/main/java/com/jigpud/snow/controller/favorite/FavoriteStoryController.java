package com.jigpud.snow.controller.favorite;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.favorite.FavoriteService;
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
public class FavoriteStoryController extends BaseController {
    private final FavoriteService favoriteService;
    private final StoryService storyService;
    private final TokenService tokenService;

    @Autowired
    FavoriteStoryController(
            FavoriteService favoriteService,
            StoryService storyService,
            TokenService tokenService) {
        this.favoriteService = favoriteService;
        this.storyService = storyService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.FAVORITE_STORY)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> favoriteStory(
            @RequestParam(value = FormDataConstant.STORY_ID, required = false, defaultValue = "") String storyId,
            HttpServletRequest request
    ) {
        if (storyService.getStory(storyId) != null) {
            String userid = tokenService.getUserid(getToken(request));
            favoriteService.favoriteStory(userid, storyId);
            if (favoriteService.haveFavoriteStory(userid, storyId)) {
                log.debug("favorite story success!");
                return Response.responseSuccess();
            } else {
                log.debug("favorite story failed!");
                return Response.responseFailed("收藏失败！");
            }
        } else {
            log.debug("story {} not exists!", storyId);
            return Response.responseFailed("游记不存在！");
        }
    }
}
