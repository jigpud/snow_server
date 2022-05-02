package com.jigpud.snow.repository.storypicture;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.StoryPicture;

/**
 * @author : jigpud
 */
public interface StoryPictureRepository {
    /**
     * 增加一张游记照片
     * @param storyPicture 游记照片
     */
    void add(StoryPicture storyPicture);

    /**
     * 是否存在该游记照片
     * @param storyId 游记
     * @param picture 照片链接
     * @return 是否存在
     */
    boolean have(String storyId, String picture);

    /**
     * 删除一张游记照片
     * @param storyId 游记
     * @param picture 照片链接
     */
    void remove(String storyId, String picture);

    /**
     * 删除一张游记照片
     * @param storyId 游记
     * @param uploaderId 上传者
     * @param picture 照片链接
     */
    void remove(String storyId, String uploaderId, String picture);

    /**
     * 获取当前游记的照片列表
     * @param storyId 游记
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 照片列表分页
     */
    Page<StoryPicture> getPictureList(String storyId, long pageSize, long currentPage);
}
