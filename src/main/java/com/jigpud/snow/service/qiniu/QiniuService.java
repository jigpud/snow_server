package com.jigpud.snow.service.qiniu;

/**
 * @author : jigpud
 */
public interface QiniuService {
    /**
     * 根据文件名生成upload token
     * @param filename 文件名
     * @return upload token
     */
    String createImgUploadToken(String filename);
}
