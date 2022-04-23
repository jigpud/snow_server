package com.jigpud.snow.service.qiniu;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : jigpud
 */
@Slf4j
@SpringBootTest
public class QiniuServiceTest {
    private final QiniuService qiniuService;

    @Autowired
    QiniuServiceTest(QiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }

    @Test
    public void testCreateUploadToken() {
        String filename = "avatar/test.jpg";
        String uploadToken = qiniuService.createImgUploadToken(filename);
        log.debug("uploadToken: {}", uploadToken);
    }
}
