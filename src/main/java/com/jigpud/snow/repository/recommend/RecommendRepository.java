package com.jigpud.snow.repository.recommend;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.User;

import java.util.List;

/**
 * @author : jigpud
 */
public interface RecommendRepository {
    /**
     * 获取热门景点列表
     * @param hotAttractionCount 需要的热门景点数量
     * @return 热门景点分页
     */
    List<Attraction> getHotAttractionList(long hotAttractionCount);

    /**
     * 获取推荐景点列表
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 推荐景点分页
     */
    Page<Attraction> getRecommendAttractionList(long pageSize, long currentPage);

    /**
     * 获取推荐用户列表
     * @param userid 用户自己
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 推荐用户列表分页
     */
    Page<User> getRecommendUserList(String userid, long pageSize, long currentPage);
}
