package com.jigpud.snow.controller.comment;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Comment;
import com.jigpud.snow.response.CommentResponse;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.comment.CommentService;
import com.jigpud.snow.service.like.LikeService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
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
public class CommentDetailController extends BaseController {
    private final CommentService commentService;
    private final UserService userService;
    private final LikeService likeService;
    private final TokenService tokenService;

    @Autowired
    CommentDetailController(
            CommentService commentService,
            UserService userService,
            LikeService likeService,
            TokenService tokenService
    ) {
        this.commentService = commentService;
        this.userService = userService;
        this.likeService = likeService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.GET_COMMENT)
    ResponseBody<CommentResponse> getComment(
            @RequestParam(FormDataConstant.COMMENT_ID) String commentId,
            HttpServletRequest request
    ) {
        Comment comment = commentService.getComment(commentId);
        if (comment != null) {
            log.debug("getComment: get comment success {}", comment);
            String userid = tokenService.getUserid(getToken(request));
            CommentResponse commentResponse = CommentResponse.create(comment, userid, userService, likeService);
            return Response.responseSuccess(commentResponse);
        } else {
            log.debug("getComment: {} not exists!", commentId);
            return Response.responseFailed("评论不存在！");
        }
    }
}
