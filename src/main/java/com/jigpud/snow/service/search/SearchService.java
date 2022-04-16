package com.jigpud.snow.service.search;

import com.jigpud.snow.model.Story;
import com.jigpud.snow.model.User;
import com.jigpud.snow.util.response.PageData;

/**
 * @author : jigpud
 */
public interface SearchService {
    /**
     * 根据关键词搜索用户
     * @param keyWords 关键词
     * @param pageCount 分页大小
     * @param page 页码
     * @return 搜索结果分页
     */
    PageData<User> searchUser(String keyWords, long pageCount, long page);

    /**
     * 根据关键词搜索游记
     * @param keyWords 关键词
     * @param pageCount 分页大小
     * @param page 页码
     * @return 搜索结果分页
     */
    PageData<Story> searchStory(String keyWords, long pageCount, long page);
}
