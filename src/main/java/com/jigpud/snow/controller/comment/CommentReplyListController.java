package com.jigpud.snow.controller.comment;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.service.comment.CommentService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.PageData;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Autowired
    CommentReplyListController(
            CommentService commentService,
            TokenService tokenService,
            UserService userService
    ) {
        this.commentService = commentService;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping(PathConstant.COMMENT_REPLY_LIST)
    ResponseBody<PageData<CommentReplyListResponse>> commentReplyList(
            @RequestParam(value = FormDataConstant.COMMENT_ID, required = false, defaultValue = "") String commentId,
            @RequestParam(value = FormDataConstant.PAGE_COUNT, required = false, defaultValue = "0") Long pageCount,
            @RequestParam(value = FormDataConstant.PAGE, required = false, defaultValue = "0") Long page,
            HttpServletRequest request
    ) {
        if (!commentId.isEmpty() && commentService.getComment(commentId) != null) {
            String userid = tokenService.getUserid(getToken(request));
            PageData<CommentReplyListResponse> commentReplyList = PageData.fromPage(
                    commentService.commentReplyList(commentId, pageCount, page),
                    comment -> {
                        String authorNickname = userService.getUserByUserid(comment.getAuthorId()).getNickname();
                        CommentReplyListResponse commentReplyListResponse = new CommentReplyListResponse();
                        commentReplyListResponse.setCommentId(comment.getCommentId());
                        commentReplyListResponse.setReplyTo(comment.getPid());
                        commentReplyListResponse.setAuthorId(comment.getAuthorId());
                        commentReplyListResponse.setAuthorNickname(authorNickname);
                        commentReplyListResponse.setContent(comment.getContent());
                        commentReplyListResponse.setLikes(commentService.likes(comment.getCommentId()));
                        commentReplyListResponse.setLiked(commentService.haveLiked(comment.getCommentId(), userid));
                        return commentReplyListResponse;
                    }
            );
            return Response.responseSuccess(commentReplyList);
        } else {
            log.debug("comment {} not exists!", commentId);
            return Response.responseFailed("评论不存在！");
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class CommentReplyListResponse {
        private String commentId;
        private String storyId;
        private String replyTo;
        private String authorId;
        private String authorNickname;
        private String content;
        private Long likes;
        private Boolean liked;
    }
}
