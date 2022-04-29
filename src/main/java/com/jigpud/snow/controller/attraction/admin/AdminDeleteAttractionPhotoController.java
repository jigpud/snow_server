package com.jigpud.snow.controller.attraction.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
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

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class AdminDeleteAttractionPhotoController extends BaseController {
    private final AttractionService attractionService;

    @Autowired
    AdminDeleteAttractionPhotoController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @PostMapping(PathConstant.ADMIN_DELETE_ATTRACTION_PHOTO)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> adminDeleteAttractionPhoto(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId,
            @RequestParam(value = FormDataConstant.PHOTO, required = false, defaultValue = "") String photo
    ) {
        if (attractionService.haveAttraction(attractionId)) {
            attractionService.deletePhoto(attractionId, photo);
            if (!attractionService.havePhoto(attractionId, photo)) {
                log.debug("delete attraction photo success!");
                return Response.responseSuccess();
            } else {
                log.debug("delete attraction photo failed!");
                return Response.responseFailed("删除景点照片失败！");
            }
        } else {
            log.debug("attraction {} not exists!", attractionId);
            return Response.responseFailed("景点不存在！");
        }
    }
}