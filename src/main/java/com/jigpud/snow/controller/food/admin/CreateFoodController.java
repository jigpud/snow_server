package com.jigpud.snow.controller.food.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.food.FoodService;
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
public class CreateFoodController extends BaseController {
    private final FoodService foodService;

    @Autowired
    CreateFoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping(PathConstant.CREATE_FOOD)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> createFood(
            @RequestParam(value = FormDataConstant.NAME, required = false, defaultValue = "") String name,
            @RequestParam(value = FormDataConstant.DESCRIPTION, required = false, defaultValue = "") String description
    ) {
        if (!name.isEmpty() && !description.isEmpty()) {
            foodService.addFood(name, description);
            return Response.responseSuccess();
        } else {
            log.debug("name or description is empty");
            return Response.responseFailed("美食信息不完整！");
        }
    }
}
