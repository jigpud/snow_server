package com.jigpud.snow.controller.attraction.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.request.UpdateAttractionRequest;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class UpdateAttractionController extends BaseController {
    private final AttractionService attractionService;
    private final TokenService tokenService;

    @Autowired
    UpdateAttractionController(AttractionService attractionService, TokenService tokenService) {
        this.attractionService = attractionService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.UPDATE_ATTRACTION)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> updateAttraction(
            @RequestBody UpdateAttractionRequest update,
            HttpServletRequest request
    ) {
        log.debug("ADMIN : update attraction: {}", update);
        String userid = tokenService.getUserid(getToken(request));
        if (update != null && attractionService.haveAttraction(update.getAttractionId())) {
            String attractionId = update.getAttractionId();
            Attraction attraction = attractionService.getAttraction(attractionId);

            // update name
            if (update.getName() != null) {
                attraction.setName(update.getName());
            } else {
                attraction.setName("");
            }

            // update description
            if (update.getDescription() != null) {
                attraction.setDescription(update.getDescription());
            } else {
                attraction.setDescription("");
            }

            // update tags
            if (update.getTags() != null) {
                attraction.setTags(update.getTags());
            } else {
                attraction.setTags(new ArrayList<>());
            }

            // update location
            if (update.getLocation() != null) {
                attraction.setLocation(update.getLocation());
            } else {
                attraction.setLocation("");
            }

            attractionService.updateAttraction(attraction);

            log.debug("ADMIN : update attraction information success!");
            return Response.responseSuccess();
        }
        log.debug("ADMIN : update attraction information failed!");
        return Response.responseFailed("更新景点信息失败！");
    }
}
