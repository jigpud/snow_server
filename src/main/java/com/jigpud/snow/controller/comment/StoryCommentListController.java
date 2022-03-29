package com.jigpud.snow.controller.comment;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.service.comment.CommentService;
import com.jigpud.snow.service.story.StoryService;
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
public class StoryCommentListController extends BaseController {
    private final StoryService storyService;
    private final CommentService commentService;
    private final TokenService tokenService;
    private final UserService userService;
    
    @Autowired
    StoryCommentListController(
            StoryService storyService,
            CommentService commentService,
            TokenService tokenService,
            UserService userService
    ) {
        this.storyService = storyService;
        this.commentService = commentService;
        this.tokenService = tokenService;
        this.userService = userService;
    }
    
    @PostMapping(PathConstant.STORY_COMMENT_LIST)
    ResponseBody<PageData<StoryCommentListResponse>> storyCommentList(
            @RequestParam(value = FormDataConstant.STORY_ID, required = false, defaultValue = "") String storyId,
            @RequestParam(value = FormDataConstant.PAGE_COUNT, required = false, defaultValue = "0") Long pageCount,
            @RequestParam(value = FormDataConstant.PAGE, required = false, defaultValue = "0") Long page,
            HttpServletRequest request
    ) {
        if (!storyId.isEmpty() && storyService.getStory(storyId) != null) {
            String userid = tokenService.getUserid(getToken(request));
            PageData<StoryCommentListResponse> storyCommentList = PageData.fromPage(
                    commentService.storyCommentList(storyId, pageCount, page),
                    comment -> {
                        String commentId = comment.getCommentId();
                        String authorNickname = userService.getUserByUserid(comment.getAuthorId()).getNickname();
                        StoryCommentListResponse storyCommentListResponse = new StoryCommentListResponse();
                        storyCommentListResponse.setStoryId(comment.getStoryId());
                        storyCommentListResponse.setCommentId(commentId);
                        storyCommentListResponse.setAuthorId(comment.getAuthorId());
                        storyCommentListResponse.setAuthorNickname(authorNickname);
                        storyCommentListResponse.setContent(comment.getContent());
                        storyCommentListResponse.setLikes(commentService.likes(commentId));
                        storyCommentListResponse.setLiked(commentService.haveLiked(commentId, userid));
                        return storyCommentListResponse;
                    }
            );
            return Response.responseSuccess(storyCommentList);
        } else {
            log.debug("story {} not exists!", storyId);
            return Response.responseFailed("游记不存在！");
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class StoryCommentListResponse {
        private String commentId;
        private String storyId;
        private String authorId;
        private String authorNickname;
        private String content;
        private Long likes;
        private Boolean liked;
    }
}
