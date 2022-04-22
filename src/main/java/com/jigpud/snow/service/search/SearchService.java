package com.jigpud.snow.service.search;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.Story;
import com.jigpud.snow.model.User;
import com.jigpud.snow.response.PageData;

/**
 * @author : jigpud
 */
public interface SearchService {
    /**
     * 根据关键词搜索用户
     * @param keyWords 关键词
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 搜索结果分页
     */
    PageData<User> searchUser(String keyWords, long pageSize, long currentPage);

    /**
     * 根据关键词搜索游记
     * @param keyWords 关键词
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 搜索结果分页
     */
    PageData<Story> searchStory(String keyWords, long pageSize, long currentPage);

    /**
     * 根据关键词搜索景点
     * @param keyWords 关键词
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 搜索结果分页
     */
    PageData<Attraction> searchAttraction(String keyWords, long pageSize, long currentPage);
}
