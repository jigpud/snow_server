package com.jigpud.snow.controller.comment;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.comment.CommentService;
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
public class CommentStoryController extends BaseController {
    private final StoryService storyService;
    private final CommentService commentService;
    private final TokenService tokenService;

    @Autowired
    CommentStoryController(
            StoryService storyService,
            CommentService commentService,
            TokenService tokenService
    ) {
        this.storyService = storyService;
        this.commentService = commentService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.COMMENT_STORY)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> commentStory(
            @RequestParam(value = FormDataConstant.STORY_ID, required = false, defaultValue = "") String storyId,
            @RequestParam(value = FormDataConstant.CONTENT, required = false, defaultValue = "") String content,
            HttpServletRequest request
    ) {
        if (!storyId.isEmpty() && storyService.getStory(storyId) != null) {
            if (!content.isEmpty()) {
                String userid = tokenService.getUserid(getToken(request));
                String commentId = commentService.comment(storyId, userid, content);
                if (commentService.getComment(commentId) != null) {
                    log.debug("comment success!");
                    return Response.responseSuccess();
                } else {
                    log.debug("comment {} failed!", storyId);
                    return Response.responseFailed("评论失败！");
                }
            } else {
                log.debug("content can not be empty!");
                return Response.responseFailed("评论不能为空！");
            }
        } else {
            log.debug("story {} not exists!", storyId);
            return Response.responseFailed("游记不存在！");
        }
    }
}
