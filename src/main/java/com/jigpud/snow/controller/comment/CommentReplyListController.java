package com.jigpud.snow.controller.comment;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Comment;
import com.jigpud.snow.response.CommentResponse;
import com.jigpud.snow.response.PageData;
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
public class CommentReplyListController extends BaseController {
    private final CommentService commentService;
    private final TokenService tokenService;
    private final UserService userService;
    private final LikeService likeService;

    @Autowired
    CommentReplyListController(
            CommentService commentService,
            TokenService tokenService,
            UserService userService,
            LikeService likeService
    ) {
        this.commentService = commentService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.likeService = likeService;
    }

    @PostMapping(PathConstant.COMMENT_REPLY_LIST)
    ResponseBody<PageData<CommentResponse>> commentReplyList(
            @RequestParam(value = FormDataConstant.COMMENT_ID, required = false, defaultValue = "") String commentId,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        if (!commentId.isEmpty() && commentService.getComment(commentId) != null) {
            String userid = tokenService.getUserid(getToken(request));
            PageData<Comment> commentList = commentService.commentReplyList(commentId, pageSize, currentPage);
            PageData<CommentResponse> commentResponseList = PageData.fromPageData(commentList, comment ->
                    CommentResponse.create(comment, userid, userService, likeService));
            return Response.responseSuccess(commentResponseList);
        } else {
            log.debug("comment {} not exists!", commentId);
            return Response.responseFailed("??????????????????");
        }
    }
}
