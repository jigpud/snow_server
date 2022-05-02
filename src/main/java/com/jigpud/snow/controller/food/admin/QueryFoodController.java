package com.jigpud.snow.controller.food.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Food;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.admin.FoodResponse;
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
public class QueryFoodController extends BaseController {
    private final FoodService foodService;

    @Autowired
    QueryFoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping(PathConstant.QUERY_FOOD)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_READ)
    ResponseBody<PageData<FoodResponse>> queryFood(
            @RequestParam(value = FormDataConstant.NAME, required = false, defaultValue = "") String name,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage
    ) {
        log.debug("query food with: {}", name);
        log.debug("query food with pageSize: {}", pageSize);
        log.debug("query food with currentPage: {}", currentPage);
        PageData<Food> foodList = foodService.getFoodNameLike(name, pageSize, currentPage);
        PageData<FoodResponse> foodResponseList = PageData.fromPageData(foodList, food ->
                FoodResponse.create(food, foodService));
        return Response.responseSuccess(foodResponseList);
    }
}
