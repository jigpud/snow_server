package com.jigpud.snow.repository.attractionpicture;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.AttractionPicture;

/**
 * @author : jigpud
 */
public interface AttractionPictureRepository {
    /**
     * 增加一张景点的照片
     * @param attractionPicture 照片
     */
    void add(AttractionPicture attractionPicture);

    /**
     * 删除一张景点的照片
     * @param attractionId 景点
     * @param uploaderId 上传者
     * @param picture 照片
     */
    void remove(String attractionId, String uploaderId, String picture);

    /**
     * 删除一张照片
     * @param attractionId 景点
     * @param picture 照片
     */
    void remove(String attractionId, String picture);

    /**
     * 获取景点的照片列表
     * @param attractionId 景点
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 照片列表分页
     */
    Page<AttractionPicture> getPictureList(String attractionId, long pageSize, long currentPage);

    /**
     * 景点中是否存在这张照片
     * @param attractionId 景点
     * @param picture 照片链接
     * @return 是否存在
     */
    boolean have(String attractionId, String picture);
}
