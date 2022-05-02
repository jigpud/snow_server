package com.jigpud.snow.repository.attractiontag;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.AttractionTag;

/**
 * @author : jigpud
 */
public interface AttractionTagRepository {
    /**
     * 为景点增加一个标签
     * @param attractionId 景点
     * @param tag 标签
     */
    void add(String attractionId, String tag);

    /**
     * 景点是否包含该标签
     * @param attractionId 景点
     * @param tag 标签
     * @return 是否包含该标签
     */
    boolean have(String attractionId, String tag);

    /**
     * 删除景点中的一个标签
     * @param attractionId 景点
     * @param tag 标签
     */
    void remove(String attractionId, String tag);

    /**
     * 获取景点的标签列表
     * @param attractionId 景点
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 标签列表分页
     */
    Page<AttractionTag> getTagList(String attractionId, long pageSize, long currentPage);
}
