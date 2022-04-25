package com.jigpud.snow.service.story;

import com.jigpud.snow.model.Story;
import com.jigpud.snow.response.PageData;

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
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 游记分页
     */
    PageData<Story> getUserStoryList(String authorId, long pageSize, long currentPage);

    /**
     * 获取用户动态的所有游记
     * @param authorId userid
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 动态游记分页
     */
    PageData<Story> getUserMomentsStoryList(String authorId, long pageSize, long currentPage);

    /**
     * 发表一篇游记
     * @param story 游记
     */
    void releaseStory(Story story);

    /**
     * 获取用户发布的游记数量
     * @param userid 用户
     * @return 游记数量
     */
    long userStoryCount(String userid);

    /**
     * 获取景点的游记数量
     * @param attractionId 景点
     * @return 游记数量
     */
    long attractionStoryCount(String attractionId);
}
