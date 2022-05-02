package com.jigpud.snow.repository.attractionfood;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.Food;

/**
 * @author : jigpud
 */
public interface AttractionFoodRepository {
    /**
     * 增加一条景点美食记录
     * @param attractionId 景点
     * @param foodId 美食
     */
    void add(String attractionId, String foodId);

    /**
     * 是否有该条景点美食记录
     * @param attractionId 景点
     * @param foodId 美食
     * @return 是否存在
     */
    boolean have(String attractionId, String foodId);

    /**
     * 删除一条景点美食记录
     * @param attractionId 景点
     * @param foodId 美食
     */
    void remove(String attractionId, String foodId);

    /**
     * 获取景点相关的美食列表
     * @param attractionId 景点
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 美食列表分页
     */
    Page<Food> getFoodList(String attractionId, long pageSize, long currentPage);

    /**
     * 获取美食相关的景点列表
     * @param foodId 美食
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 景点列表分页
     */
    Page<Attraction> getAttractionList(String foodId, long pageSize, long currentPage);
}
