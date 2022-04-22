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
public class DeleteAttractionController extends BaseController {
    private final AttractionService attractionService;

    @Autowired
    DeleteAttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @PostMapping(PathConstant.DELETE_ATTRACTION)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> deleteAttraction(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId
    ) {
        attractionService.deleteAttraction(attractionId);
        if (!attractionService.haveAttraction(attractionId)) {
            log.debug("ADMIN : delete attraction success!");
            return Response.responseSuccess();
        } else {
            log.debug("ADMIN : delete attraction failed!");
            return Response.responseFailed("删除景点失败！");
        }
    }
}
