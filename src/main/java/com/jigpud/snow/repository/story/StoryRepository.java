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
}
