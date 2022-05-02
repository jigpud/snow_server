package com.jigpud.snow.controller.food;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.FoodPictureResponse;
import com.jigpud.snow.response.PageData;
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
public class UploadFoodPictureController extends BaseController {
    private final FoodService foodService;
    private final TokenService tokenService;

    @Autowired
    UploadFoodPictureController(FoodService foodService, TokenService tokenService) {
        this.foodService = foodService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.UPLOAD_FOOD_PICTURE)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<PageData<FoodPictureResponse>> getFoodPictureList(
            @RequestParam(value = FormDataConstant.FOOD_ID, required = false, defaultValue = "") String foodId,
            @RequestParam(value = FormDataConstant.PICTURE, required = false, defaultValue = "") String picture,
            HttpServletRequest request
    ) {
        if (foodService.haveFood(foodId)) {
            if (!picture.isEmpty()) {
                if (!foodService.havePicture(foodId, picture)) {
                    String userid = tokenService.getUserid(getToken(request));
                    foodService.addPicture(foodId, userid, picture);
                    if (foodService.havePicture(foodId, picture)) {
                        log.debug("upload food picture success!");
                        return Response.responseSuccess();
                    } else {
                        log.debug("upload food picture failed!");
                        return Response.responseFailed("上传美食照片失败！");
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
            log.debug("food {} not exists", foodId);
            return Response.responseFailed("美食不存在！");
        }
    }
}
