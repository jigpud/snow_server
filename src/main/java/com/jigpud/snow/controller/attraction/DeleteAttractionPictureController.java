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
public class DeleteAttractionPictureController extends BaseController {
    private final AttractionService attractionService;
    private final TokenService tokenService;

    @Autowired
    DeleteAttractionPictureController(AttractionService attractionService, TokenService tokenService) {
        this.attractionService = attractionService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.DELETE_ATTRACTION_PICTURE)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> deleteAttractionPicture(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId,
            @RequestParam(value = FormDataConstant.PICTURE, required = false, defaultValue = "") String picture,
            HttpServletRequest request
    ) {
        if (attractionService.haveAttraction(attractionId)) {
            String userid = tokenService.getUserid(getToken(request));
            attractionService.deletePicture(attractionId, userid, picture);
            if (!attractionService.havePicture(attractionId, picture)) {
                log.debug("delete attraction picture success!");
                return Response.responseSuccess();
            } else {
                log.debug("delete attraction picture failed!");
                return Response.responseFailed("删除景点照片失败！");
            }
        } else {
            log.debug("attraction {} not exists!", attractionId);
            return Response.responseFailed("景点不存在！");
        }
    }
}
