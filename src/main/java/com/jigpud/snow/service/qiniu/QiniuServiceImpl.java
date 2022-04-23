package com.jigpud.snow.service.qiniu;

import com.jigpud.snow.config.QiniuConfig;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class QiniuServiceImpl implements QiniuService {
    private final Auth auth;
    private final QiniuConfig qiniuConfig;

    @Autowired
    QiniuServiceImpl(QiniuConfig qiniuConfig) {
        auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
        this.qiniuConfig = qiniuConfig;
    }

    @Override
    public String createImgUploadToken(String filename) {
        StringMap returnBody = new StringMap();
        returnBody.put("url", "https://" + qiniuConfig.getDomain() + "/" + filename);
        StringMap policy = new StringMap();
        policy.put("returnBody", returnBody.jsonString());
        policy.put("mimeLimit", "image/jpeg;image/png");
        return auth.uploadToken(qiniuConfig.getBucketName(), filename, qiniuConfig.getUploadTokenExpiration(), policy);
    }
}
