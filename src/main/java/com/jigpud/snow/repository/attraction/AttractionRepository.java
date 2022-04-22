package com.jigpud.snow.repository.attraction;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Attraction;

/**
 * @author : jigpud
 */
public interface AttractionRepository {
    /**
     * 新增一个景点
     * @param attraction 景点
     */
    void add(Attraction attraction);

    /**
     * 更新一个景点
     * @param attraction 景点
     */
    void update(Attraction attraction);

    /**
     * 删除一个景点
     * @param attractionId 景点
     */
    void remove(String attractionId);

    /**
     * 是否存在景点
     * @param attractionId 景点
     * @return 是否存在
     */
    boolean have(String attractionId);

    /**
     * 根据景点id获取一个景点
     * @param attractionId 景点id
     * @return 景点
     */
    Attraction getAttraction(String attractionId);

    /**
     * 根据景点名称模糊匹配景点
     * @param name 景点名称
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 景点列表分页
     */
    Page<Attraction> attractionsNameLike(String name, long pageSize, long currentPage);

    /**
     * 获取景点列表
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 景点列表分页
     */
    Page<Attraction> getAttractionList(long pageSize, long currentPage);

    /**
     * 根据关键词模糊搜索景点
     * @param keyWords 关键词
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 搜索结果分页
     */
    Page<Attraction> blurSearch(String keyWords, long pageSize, long currentPage);
}
