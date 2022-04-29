package com.jigpud.snow.controller.attraction;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class UploadAttractionPhotoController extends BaseController {
    private final AttractionService attractionService;
    private final TokenService tokenService;

    @Autowired
    UploadAttractionPhotoController(AttractionService attractionService, TokenService tokenService) {
        this.attractionService = attractionService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.UPLOAD_ATTRACTION_PHOTO)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> uploadAttractionPhoto(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId,
            @RequestParam(value = FormDataConstant.PHOTO, required = false, defaultValue = "") String photo,
            HttpServletRequest request
    ) {
        if (attractionService.haveAttraction(attractionId)) {
            if (!photo.isEmpty()) {
                if (!attractionService.havePhoto(attractionId, photo)) {
                    String userid = tokenService.getUserid(getToken(request));
                    attractionService.addPhoto(attractionId, userid, photo);
                    if (attractionService.havePhoto(attractionId, photo)) {
                        log.debug("upload attraction photo success!");
                        return Response.responseSuccess();
                    } else {
                        log.debug("upload attraction photo failed!");
                        return Response.responseFailed("上传景点照片失败！");
                    }
                } else {
                    log.debug("photo already exists!");
                    return Response.responseFailed("照片已存在！");
                }
            } else {
                log.debug("photo is empty!");
                return Response.responseFailed("照片地址不能为空！");
            }
        } else {
            log.debug("attraction {} not exists!", attractionId);
            return Response.responseFailed("景点不存在！");
        }
    }
}
