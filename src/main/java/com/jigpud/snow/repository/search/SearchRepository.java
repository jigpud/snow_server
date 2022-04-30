package com.jigpud.snow.repository.search;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.model.User;

/**
 * @author : jigpud
 */
public interface SearchRepository {
    /**
     * 根据关键词模糊搜索用户
     * @param keyWords 关键词
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 模糊搜索结果分页
     */
    Page<User> searchUser(String keyWords, long pageSize, long currentPage);

    /**
     * 根据关键词模糊搜索游记
     * @param keyWords 关键词
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 模糊搜索结果分页
     */
    Page<Story> searchStory(String keyWords, long pageSize, long currentPage);

    /**
     * 根据关键词模糊搜索景点
     * @param keyWords 关键词
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 模糊搜索结果分页
     */
    Page<Attraction> searchAttraction(String keyWords, long pageSize, long currentPage);
}
