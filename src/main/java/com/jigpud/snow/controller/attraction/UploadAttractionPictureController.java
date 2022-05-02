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
public class UploadAttractionPictureController extends BaseController {
    private final AttractionService attractionService;
    private final TokenService tokenService;

    @Autowired
    UploadAttractionPictureController(AttractionService attractionService, TokenService tokenService) {
        this.attractionService = attractionService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.UPLOAD_ATTRACTION_PICTURE)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> uploadAttractionPicture(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId,
            @RequestParam(value = FormDataConstant.PICTURE, required = false, defaultValue = "") String picture,
            HttpServletRequest request
    ) {
        if (attractionService.haveAttraction(attractionId)) {
            if (!picture.isEmpty()) {
                if (!attractionService.havePicture(attractionId, picture)) {
                    String userid = tokenService.getUserid(getToken(request));
                    attractionService.addPicture(attractionId, userid, picture);
                    if (attractionService.havePicture(attractionId, picture)) {
                        log.debug("upload attraction picture success!");
                        return Response.responseSuccess();
                    } else {
                        log.debug("upload attraction picture failed!");
                        return Response.responseFailed("上传景点照片失败！");
                    }
                } else {
                    log.debug("picture already exists!");
                    return Response.responseFailed("照片已存在！");
                }
            } else {
                log.debug("picture is empty!");
                return Response.responseFailed("照片地址不能为空！");
            }
        } else {
            log.debug("attraction {} not exists!", attractionId);
            return Response.responseFailed("景点不存在！");
        }
    }
}
