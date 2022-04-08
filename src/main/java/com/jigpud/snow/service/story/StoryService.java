package com.jigpud.snow.service.story;

import com.jigpud.snow.model.Story;
import com.jigpud.snow.util.response.PageData;

/**
 * @author : jigpud
 * 游记相关服务
 */
public interface StoryService {
    /**
     * 获取游记
     * @param storyId storyId
     * @return 游记
     */
    Story getStory(String storyId);

    /**
     * 获取用户的所有游记
     * @param authorId userid
     * @param pageCount 分页大小
     * @param page 页码
     * @return 游记分页
     */
    PageData<Story> getUserStoryList(String authorId, long pageCount, long page);

    /**
     * 获取用户动态的所有游记
     * @param authorId userid
     * @param pageCount 分页大小
     * @param page 页码
     * @return 动态游记分页
     */
    PageData<Story> getUserMomentsStoryList(String authorId, long pageCount, long page);

    /**
     * 发表一篇游记
     * @param story 游记
     */
    void releaseStory(Story story);

    /**
     * 获取点赞数
     * @param storyId 游记
     * @return 点赞数
     */
    long likes(String storyId);

    /**
     * 减少点赞数
     */
    void like(String storyId, String userid);

    /**
     * 增加点赞数
     */
    void unlike(String storyId, String userid);

    /**
     * 用户是否点赞
     * @param storyId 游记
     * @param userid 用户
     * @return 是否点赞
     */
    boolean haveLiked(String storyId, String userid);

    /**
     * 根据关键词搜索游记
     * @param keyWords 关键词
     * @param pageCount 分页大小
     * @param page 页码
     * @return 搜索结果分页
     */
    PageData<Story> searchStory(String keyWords, long pageCount, long page);
}
