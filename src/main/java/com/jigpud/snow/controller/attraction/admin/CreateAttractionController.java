package com.jigpud.snow.controller.attraction.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.encrypt.Encryptor;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class CreateAttractionController extends BaseController {
    private final AttractionService attractionService;

    @Autowired
    CreateAttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @PostMapping(PathConstant.ADD_ATTRACTION)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> addAttraction(
            @RequestParam(value = FormDataConstant.NAME, required = false, defaultValue = "") String name,
            @RequestParam(value = FormDataConstant.DESCRIPTION, required = false, defaultValue = "") String description,
            @RequestParam(value = FormDataConstant.LOCATION, required = false, defaultValue = "") String location,
            @RequestParam(value = FormDataConstant.TAGS, required = false, defaultValue = "") String[] tags
    ) {
        if (name.isEmpty() || description.isEmpty() || location.isEmpty()) {
            return Response.responseFailed("景点信息不完整！");
        }
        log.debug("\n" +
                "name: {}\n" +
                "description: {}\n" +
                "location: {}\n" +
                "tags: {}", name, description, location, Arrays.toString(tags));
        // create attraction
        String attractionId = Encryptor.uuid();
        Attraction attraction = new Attraction();
        attraction.setAttractionId(attractionId);
        attraction.setName(name);
        attraction.setDescription(description);
        attraction.setLocation(location);
        attractionService.addAttraction(attraction);

        // add tags
        Arrays.stream(tags).forEach(tag -> attractionService.addTag(attractionId, tag));

        if (attractionService.haveAttraction(attractionId)) {
            log.debug("ADMIN : add attraction success: {}", attraction);
            return Response.responseSuccess();
        } else {
            log.debug("ADMIN : add attraction failed!");
            return Response.responseFailed("添加景点失败！");
        }
    }
}
