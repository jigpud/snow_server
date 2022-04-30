package com.jigpud.snow.repository.story;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Story;

/**
 * @author : jigpud
 * 游记
 */
public interface StoryRepository {
    /**
     * 新增一篇游记
     * @param story 游记
     */
    void addStory(Story story);

    /**
     * 根据storyId获取游记详情
     * @param storyId storyId
     * @return 游记详情
     */
    Story getStory(String storyId);

    /**
     * 获取用户的所有游记
     * @param userid userid
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 分页
     */
    Page<Story> getUserStoryList(String userid, long pageSize, long currentPage);

    /**
     * 获取景点的游记列表
     * @param attractionId 景点
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 游记列表分页
     */
    Page<Story> getAttractionStoryList(String attractionId, long pageSize, long currentPage);

    /**
     * 更新游记
     * @param story 游记
     */
    void update(Story story);

    /**
     * 根据关键词模糊搜索游记
     * @param keyWords 关键词
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 模糊搜索结果分页
     */
    Page<Story> blurSearch(String keyWords, long pageSize, long currentPage);

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

    /**
     * 获取用户的动态游记列表
     * @param userid 用户
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 动态游记列表分页
     */
    Page<Story> getMomentsStoryList(String userid, long pageSize, long currentPage);
}
