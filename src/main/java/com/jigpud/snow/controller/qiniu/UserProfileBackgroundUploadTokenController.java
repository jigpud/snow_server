package com.jigpud.snow.controller.qiniu;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.UploadTokenResponse;
import com.jigpud.snow.service.qiniu.QiniuService;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.encrypt.Encryptor;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class UserProfileBackgroundUploadTokenController extends BaseController {
    private final QiniuService qiniuService;

    @Autowired
    UserProfileBackgroundUploadTokenController(QiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }

    @GetMapping(PathConstant.GET_USER_PROFILE_BACKGROUND_IMG_UPLOAD_TOKEN)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<UploadTokenResponse> getUserProfileBackgroundImgUploadToken() {
        String filename = "userProfileBackground/" + Encryptor.uuid();
        String uploadToken = qiniuService.createImgUploadToken(filename);
        log.debug("userProfileBackgroundImgUploadToken -> uploadToken: {}, filename: {}", uploadToken, filename);
        UploadTokenResponse uploadTokenResponse = UploadTokenResponse.create(uploadToken, filename);
        return Response.responseSuccess(uploadTokenResponse);
    }
}
