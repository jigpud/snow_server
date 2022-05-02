package com.jigpud.snow.repository.food;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.Food;

/**
 * @author : jigpud
 */
public interface FoodRepository {
    /**
     * 根据id获取美食
     * @param foodId 美食
     * @return 美食
     */
    Food get(String foodId);

    /**
     * 根据名称获取美食
     * @param name 名称
     * @return 美食
     */
    Food getByName(String name);

    /**
     * 获取美食列表
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 美食列表分页
     */
    Page<Food> getFoodList(long pageSize, long currentPage);

    /**
     * 增加一种美食
     * @param foodId 美食id
     * @param name 名称
     * @param description 简介
     */
    void add(String foodId, String name, String description);

    /**
     * 是否已有该名称的美食
     * @param name 名称
     * @return 该美食是否已存在
     */
    boolean haveNameIs(String name);

    /**
     * 是否存在该美食
     * @param foodId 美食
     * @return 是否存在
     */
    boolean have(String foodId);

    /**
     * 删除一种美食
     * @param foodId 美食
     */
    void remove(String foodId);

    /**
     * 根据美食名称删除一种美食
     * @param name 名称
     */
    void removeByName(String name);

    /**
     * 更新美食信息
     * @param foodId 美食
     * @param name 名称
     * @param description 简介
     */
    void update(String foodId, String name, String description);

    /**
     * 根据名称模糊查询美食
     * @param name 名称
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 没事列表分页
     */
    Page<Food> getFoodNameLike(String name, long pageSize, long currentPage);
}
