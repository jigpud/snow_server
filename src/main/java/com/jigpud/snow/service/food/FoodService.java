package com.jigpud.snow.service.food;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.Food;
import com.jigpud.snow.model.FoodPicture;
import com.jigpud.snow.response.PageData;

/**
 * @author : jigpud
 */
public interface FoodService {
    /**
     * 获取美食
     * @param foodId 美食
     * @return 美食
     */
    Food getFood(String foodId);

    /**
     * 是否存在该美食
     * @param foodId 美食
     * @return 是否存在
     */
    boolean haveFood(String foodId);

    /**
     * 新增一种美食
     * @param name 名称
     * @param description 简介
     */
    void addFood(String name, String description);

    /**
     * 新增一张美食照片
     * @param foodId 美食
     * @param userid 上传者
     * @param picture 照片链接
     */
    void addPicture(String foodId, String userid, String picture);

    /**
     * 删除一张美食照片
     * @param foodId 美食
     * @param userid 上传者
     * @param picture 照片链接
     */
    void deletePicture(String foodId, String userid, String picture);

    /**
     * 删除一张美食照片
     * @param foodId 美食
     * @param picture 照片链接
     */
    void deletePicture(String foodId, String picture);

    /**
     * 是否存在该美食照片
     * @param foodId 美食
     * @param picture 照片地址
     * @return 是否存在
     */
    boolean havePicture(String foodId, String picture);

    /**
     * 获取美食列表
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 美食列表分页
     */
    PageData<Food> getFoodList(long pageSize, long currentPage);

    /**
     * 获取美食的照片列表
     * @param foodId 美食
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 照片列表分页
     */
    PageData<FoodPicture> getPictureList(String foodId, long pageSize, long currentPage);

    /**
     * 为美食添加相关景点
     * @param foodId 美食
     * @param attractionId 景点
     */
    void addAttraction(String foodId, String attractionId);

    /**
     * 获取美食的相关景点是否包含该景点
     * @param foodId 美食
     * @param attractionId 景点
     * @return 是否包含该景点
     */
    boolean haveAttraction(String foodId, String attractionId);

    /**
     * 获取美食相关的景点列表
     * @param foodId 美食
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 景点列表分页
     */
    PageData<Attraction> getAttractionList(String foodId, long pageSize, long currentPage);

    /**
     * 删除一个美食
     * @param foodId 美食
     */
    void deleteFood(String foodId);

    /**
     * 更新美食信息
     * @param foodId 美食
     * @param name 名称
     * @param description 简介
     */
    void updateFood(String foodId, String name, String description);

    /**
     * 根据名称模糊查询美食
     * @param name 名称
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 美食列表分页
     */
    PageData<Food> getFoodNameLike(String name, long pageSize, long currentPage);
}
