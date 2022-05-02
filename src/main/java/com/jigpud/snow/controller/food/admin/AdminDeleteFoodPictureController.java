package com.jigpud.snow.controller.food.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.food.FoodService;
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
public class AdminDeleteFoodPictureController extends BaseController {
    private final FoodService foodService;

    @Autowired
    AdminDeleteFoodPictureController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping(PathConstant.ADMIN_DELETE_FOOD_PICTURE)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> adminDeleteFoodPicture(
            @RequestParam(value = FormDataConstant.FOOD_ID, required = false, defaultValue = "") String foodId,
            @RequestParam(value = FormDataConstant.PICTURE, required = false, defaultValue = "") String picture
    ) {
        if (foodService.haveFood(foodId)) {
            foodService.deletePicture(foodId, picture);
            if (!foodService.havePicture(foodId, picture)) {
                log.debug("delete food picture success!");
                return Response.responseSuccess();
            } else {
                log.debug("delete food picture failed!");
                return Response.responseFailed("删除美食照片失败！");
            }
        } else {
            log.debug("food {} not exists!", foodId);
            return Response.responseFailed("美食不存在！");
        }
    }
}
