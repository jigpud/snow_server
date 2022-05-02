package com.jigpud.snow.controller.food;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Food;
import com.jigpud.snow.response.FoodResponse;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.food.FoodService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class FoodListController extends BaseController {
    private final FoodService foodService;

    @Autowired
    FoodListController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping(PathConstant.GET_FOOD_LIST)
    ResponseBody<PageData<FoodResponse>> getFoodList(
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage
    ) {
        log.debug("get food list with pageSize: {}", pageSize);
        log.debug("get food list with currentPage: {}", currentPage);
        PageData<Food> foodList = foodService.getFoodList(pageSize, currentPage);
        PageData<FoodResponse> foodResponseList = PageData.fromPageData(foodList, food -> FoodResponse.create(food, foodService));
        return Response.responseSuccess(foodResponseList);
    }
}
