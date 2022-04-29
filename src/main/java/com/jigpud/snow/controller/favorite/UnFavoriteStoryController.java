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
public class UnFavoriteStoryController extends BaseController {
    private final StoryService storyService;
    private final FavoriteService favoriteService;
    private final TokenService tokenService;

    @Autowired
    UnFavoriteStoryController(
            StoryService storyService,
            FavoriteService favoriteService,
            TokenService tokenService
    ) {
        this.storyService = storyService;
        this.favoriteService = favoriteService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.UN_FAVORITE_STORY)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> unFavoriteStory(
            @RequestParam(value = FormDataConstant.STORY_ID, required = false, defaultValue = "") String storyId,
            HttpServletRequest request
    ) {
        if (storyService.getStory(storyId) != null) {
            String userid = tokenService.getUserid(getToken(request));
            favoriteService.unFavoriteStory(storyId, userid);
            if (!favoriteService.haveFavoriteStory(storyId, userid)) {
                log.debug("un favorite story success!");
                return Response.responseSuccess();
            } else {
                log.debug("un favorite story failed!");
                return Response.responseFailed("取消收藏失败！");
            }
        } else {
            log.debug("story {} not exists!", storyId);
            return Response.responseFailed("游记不存在！");
        }
    }
}
