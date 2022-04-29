package com.jigpud.snow.service.attraction;

import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.AttractionPhoto;
import com.jigpud.snow.response.PageData;

import java.util.List;

/**
 * @author : jigpud
 */
public interface AttractionService {
    /**
     * 增加一个景点
     * @param attractionId 景点
     */
    void addAttraction(Attraction attractionId);

    /**
     * 删除一个景点
     * @param attractionId 景点
     */
    void deleteAttraction(String attractionId);

    /**
     * 判断景点是否存在
     * @param attractionId 景点
     * @return 是否存在
     */
    boolean haveAttraction(String attractionId);

    /**
     * 更新景点信息
     * @param attraction 景点
     */
    void updateAttraction(Attraction attraction);

    /**
     * 获取景点
     * @param attractionId 景点
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
    PageData<Attraction> attractionsNameLike(String name, long pageSize, long currentPage);

    /**
     * 获取景点列表
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 景点列表分页
     */
    PageData<Attraction> getAttractionList(long pageSize, long  currentPage);

    /**
     * 为景点打分
     * @param attractionId 景点
     * @param userid 用户
     * @param score 分数
     */
    void score(String attractionId, String userid, int score);

    /**
     * 获取景点已打分的人数
     * @param attractionId 景点
     * @return 已打分的人数
     */
    long scoreCount(String attractionId);

    /**
     * 获取景点的评分
     * @param attractionId 景点
     * @return 平均分
     */
    float getScore(String attractionId);

    /**
     * 获取用户的打分
     * @param attractionId 景点
     * @param userid 用户
     * @return 打分
     */
    int getUserScore(String attractionId, String userid);

    /**
     * 获取用户是否已经为景点打分
     * @param attractionId 景点
     * @param userid 用户
     * @return 是否已经打分
     */
    boolean haveScored(String attractionId, String userid);

    /**
     * 增加一张景点照片
     * @param attractionId 景点
     * @param userid 上传者
     * @param photo 照片链接
     */
    void addPhoto(String attractionId, String userid, String photo);

    /**
     * 删除一张景点照片
     * @param attractionId 景点
     * @param userid 上传者
     * @param photo 照片链接
     */
    void deletePhoto(String attractionId, String userid, String photo);

    /**
     * 删除一张照片
     * @param attractionId 景点
     * @param photo 照片链接
     */
    void deletePhoto(String attractionId, String photo);

    /**
     * 获取景点的照片列表
     * @param attractionId 景点
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 照片列表分页
     */
    PageData<AttractionPhoto> getAttractionPhotoList(String attractionId, long pageSize, long currentPage);

    /**
     * 景点中是否存在这张照片
     * @param attractionId 景点
     * @param photo 照片链接
     * @return 是否存在
     */
    boolean havePhoto(String attractionId, String photo);
}
