package com.jigpud.snow.controller.comment;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.comment.CommentService;
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
public class ReplyCommentController extends BaseController {
    private final CommentService commentService;
    private final TokenService tokenService;

    @Autowired
    ReplyCommentController(CommentService commentService, TokenService tokenService) {
        this.commentService = commentService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.REPLY_COMMENT)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> replyComment(
            @RequestParam(value = FormDataConstant.COMMENT_ID, required = false, defaultValue = "") String commentId,
            @RequestParam(value = FormDataConstant.CONTENT, required = false, defaultValue = "") String content,
            HttpServletRequest request
    ) {
        if (!commentId.isEmpty() && commentService.getComment(commentId) != null) {
            if (!content.isEmpty()) {
                String userid = tokenService.getUserid(getToken(request));
                String replyCommentId = commentService.reply(commentId, userid, content);
                if (commentService.getComment(replyCommentId) != null) {
                    log.debug("reply success!");
                    return Response.responseSuccess();
                } else {
                    log.debug("reply {} failed!", commentId);
                    return Response.responseFailed("???????????????");
                }
            } else {
                log.debug("content can not be empty!");
                return Response.responseFailed("?????????????????????");
            }
        } else {
            log.debug("comment {} not exists!", commentId);
            return Response.responseFailed("??????????????????");
        }
    }
}
