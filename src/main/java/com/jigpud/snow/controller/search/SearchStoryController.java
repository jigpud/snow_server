package com.jigpud.snow.controller.search;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.service.story.StoryService;
import com.jigpud.snow.service.token.TokenService;
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
import java.util.List;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class SearchStoryController extends BaseController {
    private final StoryService storyService;
    private final TokenService tokenService;

    @Autowired
    SearchStoryController(StoryService storyService, TokenService tokenService) {
        this.storyService = storyService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.SEARCH_STORY)
    ResponseBody<PageData<SearchStoryResponse>> searchStory(
            @RequestParam(value = FormDataConstant.KEY_WORDS, required = false, defaultValue = "") String keyWords,
            @RequestParam(value = FormDataConstant.PAGE_COUNT, required = false, defaultValue = "0") Long pageCount,
            @RequestParam(value = FormDataConstant.PAGE, required = false, defaultValue = "0") Long page,
            HttpServletRequest request
    ) {
        if (!keyWords.isEmpty()) {
            String selfUserid = tokenService.getUserid(getToken(request));
            PageData<Story> storyPageData = storyService.searchStory(keyWords, pageCount, page);
            PageData<SearchStoryResponse> searchStoryResponsePageData = PageData.fromPageData(storyPageData, story -> {
                String storyId = story.getStoryId();
                SearchStoryResponse searchStoryResponse = new SearchStoryResponse();
                searchStoryResponse.setStoryId(storyId);
                searchStoryResponse.setAuthorId(story.getAuthorId());
                searchStoryResponse.setTitle(story.getTitle());
                searchStoryResponse.setContent(story.getContent());
                searchStoryResponse.setPictures(story.getPictures());
                searchStoryResponse.setReleaseTime(story.getReleaseTime());
                searchStoryResponse.setReleaseLocation(story.getReleaseLocation());
                searchStoryResponse.setAttractionId(story.getAttractionId());
                searchStoryResponse.setLiked(storyService.haveLiked(storyId, selfUserid));
                searchStoryResponse.setLikes(storyService.likes(story.getStoryId()));
                return searchStoryResponse;
            });
            return Response.responseSuccess(searchStoryResponsePageData);
        } else {
            log.debug("searchStory: keyWords is empty!");
            return Response.responseFailed("关键词不能为空！");
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class SearchStoryResponse {
        private String storyId;
        private String authorId;
        private String title;
        private String content;
        private Long likes;
        private List<String> pictures;
        private Long releaseTime;
        private String releaseLocation;
        private Boolean liked;
        private String attractionId;
    }
}
