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
public class AdminDeleteAttractionPictureController extends BaseController {
    private final AttractionService attractionService;

    @Autowired
    AdminDeleteAttractionPictureController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @PostMapping(PathConstant.ADMIN_DELETE_ATTRACTION_PICTURE)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> adminDeleteAttractionPicture(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId,
            @RequestParam(value = FormDataConstant.PICTURE, required = false, defaultValue = "") String picture
    ) {
        if (attractionService.haveAttraction(attractionId)) {
            attractionService.deletePicture(attractionId, picture);
            if (!attractionService.havePicture(attractionId, picture)) {
                log.debug("delete attraction picture success!");
                return Response.responseSuccess();
            } else {
                log.debug("delete attraction picture failed!");
                return Response.responseFailed("???????????????????????????");
            }
        } else {
            log.debug("attraction {} not exists!", attractionId);
            return Response.responseFailed("??????????????????");
        }
    }
}
