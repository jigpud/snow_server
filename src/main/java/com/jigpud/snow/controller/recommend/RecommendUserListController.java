package com.jigpud.snow.controller.recommend;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.UserInformationResponse;
import com.jigpud.snow.service.favorite.FavoriteService;
import com.jigpud.snow.service.follow.FollowService;
import com.jigpud.snow.service.like.LikeService;
import com.jigpud.snow.service.recommend.RecommendService;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
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
public class RecommendUserListController extends BaseController {
    private final RecommendService recommendService;
    private final TokenService tokenService;
    private final FollowService followService;
    private final LikeService likeService;
    private final StoryService storyService;
    private final FavoriteService favoriteService;

    @Autowired
    RecommendUserListController(
            RecommendService recommendService,
            TokenService tokenService,
            FollowService followService,
            LikeService likeService,
            StoryService storyService,
            FavoriteService favoriteService
    ) {
        this.recommendService = recommendService;
        this.tokenService = tokenService;
        this.followService = followService;
        this.likeService = likeService;
        this.storyService = storyService;
        this.favoriteService = favoriteService;
    }

    @PostMapping(PathConstant.GET_RECOMMEND_USER_LIST)
    ResponseBody<PageData<UserInformationResponse>> getRecommendUserList(
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        log.debug("get recommend user list with pageSize: {}", pageSize);
        log.debug("get recommend user list with currentPage: {}", currentPage);
        String userid = tokenService.getUserid(getToken(request));
        PageData<User> recommendUserList = recommendService.getRecommendUserList(userid, pageSize, currentPage);
        PageData<UserInformationResponse> recommendUserInformationResponseList = PageData.fromPageData(recommendUserList, user ->
                UserInformationResponse.create(user, userid, followService, likeService, storyService, favoriteService));
        log.debug("get recommend user list: {}", recommendUserInformationResponseList);
        return Response.responseSuccess(recommendUserInformationResponseList);
    }
}
