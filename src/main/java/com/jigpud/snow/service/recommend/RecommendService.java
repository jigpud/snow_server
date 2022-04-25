package com.jigpud.snow.service.recommend;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.User;
import com.jigpud.snow.response.PageData;

import java.util.List;

/**
 * @author : jigpud
 */
public interface RecommendService {
    /**
     * 获取热门景点列表
     * @param hotAttractionCount 需要热门景点数量
     * @return 热门景点分页
     */
    List<Attraction> getHotAttractionList(long hotAttractionCount);

    /**
     * 获取推荐景点列表
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 推荐景点分页
     */
    PageData<Attraction> getRecommendAttractionList(long pageSize, long currentPage);

    /**
     * 获取推荐用户列表
     * @param userid 用户
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 推荐用户列表分页
     */
    PageData<User> getRecommendUserList(String userid, long pageSize, long currentPage);
}
