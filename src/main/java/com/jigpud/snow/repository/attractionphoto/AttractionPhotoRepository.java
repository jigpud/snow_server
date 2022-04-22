package com.jigpud.snow.repository.attractionphoto;

import com.jigpud.snow.model.AttractionPhoto;

import java.util.List;

/**
 * @author : jigpud
 */
public interface AttractionPhotoRepository {
    /**
     * 增加一张景点的照片
     * @param attractionPhoto 照片
     */
    void add(AttractionPhoto attractionPhoto);

    /**
     * 删除一张景点的照片
     * @param uploaderId 上传者
     * @param attractionId 景点
     * @param photo 照片
     */
    void remove(String uploaderId, String attractionId, String photo);

    /**
     * 获取景点的照片列表
     * @param attractionId 景点
     * @return 照片列表分页
     */
    List<AttractionPhoto> getAttractionPhotoList(String attractionId);

    /**
     * 景点中是否存在这张照片
     * @param attractionId 景点
     * @param photo 照片链接
     * @return 是否存在
     */
    boolean have(String attractionId, String photo);
}
