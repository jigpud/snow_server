package com.jigpud.snow.controller.comment;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.CommentResponse;
import com.jigpud.snow.service.comment.CommentService;
import com.jigpud.snow.service.like.LikeService;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.PageData;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
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
public class StoryCommentListController extends BaseController {
    private final StoryService storyService;
    private final CommentService commentService;
    private final TokenService tokenService;
    private final UserService userService;
    private final LikeService likeService;
    
    @Autowired
    StoryCommentListController(
            StoryService storyService,
            CommentService commentService,
            TokenService tokenService,
            UserService userService,
            LikeService likeService
    ) {
        this.storyService = storyService;
        this.commentService = commentService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.likeService = likeService;
    }
    
    @PostMapping(PathConstant.STORY_COMMENT_LIST)
    ResponseBody<PageData<CommentResponse>> storyCommentList(
            @RequestParam(value = FormDataConstant.STORY_ID, required = false, defaultValue = "") String storyId,
            @RequestParam(value = FormDataConstant.PAGE_COUNT, required = false, defaultValue = "0") Long pageCount,
            @RequestParam(value = FormDataConstant.PAGE, required = false, defaultValue = "0") Long page,
            HttpServletRequest request
    ) {
        if (!storyId.isEmpty() && storyService.getStory(storyId) != null) {
            String userid = tokenService.getUserid(getToken(request));
            PageData<CommentResponse> storyCommentList = PageData.fromPage(
                    commentService.storyCommentList(storyId, pageCount, page),
                    comment -> {
                        String commentId = comment.getCommentId();
                        String authorNickname = userService.getUserByUserid(comment.getAuthorId()).getNickname();
                        CommentResponse commentResponse = new CommentResponse();
                        commentResponse.setStoryId(comment.getStoryId());
                        commentResponse.setCommentId(commentId);
                        commentResponse.setAuthorId(comment.getAuthorId());
                        commentResponse.setAuthorNickname(authorNickname);
                        commentResponse.setContent(comment.getContent());
                        commentResponse.setLikes(likeService.commentLikes(commentId));
                        commentResponse.setLiked(likeService.haveLikedComment(userid, commentId));
                        return commentResponse;
                    }
            );
            return Response.responseSuccess(storyCommentList);
        } else {
            log.debug("story {} not exists!", storyId);
            return Response.responseFailed("游记不存在！");
        }
    }
}
