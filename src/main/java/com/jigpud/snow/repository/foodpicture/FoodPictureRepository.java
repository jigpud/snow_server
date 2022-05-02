package com.jigpud.snow.repository.foodpicture;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.FoodPicture;

/**
 * @author : jigpud
 */
public interface FoodPictureRepository {
    /**
     * 增加一张美食照片
     * @param foodPicture 美食照片
     */
    void add(FoodPicture foodPicture);

    /**
     * 是否存在该美食照片
     * @param foodId 美食
     * @param picture 照片链接
     * @return 是否存在
     */
    boolean have(String foodId, String picture);

    /**
     * 删除一张美食照片
     * @param foodId 美食
     * @param picture 照片链接
     */
    void remove(String foodId, String picture);

    /**
     * 删除一张美食照片
     * @param foodId 美食
     * @param uploaderId 上传者
     * @param picture 照片链接
     */
    void remove(String foodId, String uploaderId, String picture);

    /**
     * 获取当前美食的照片列表
     * @param foodId 美食
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 照片列表分页
     */
    Page<FoodPicture> getPictureList(String foodId, long pageSize, long currentPage);
}
