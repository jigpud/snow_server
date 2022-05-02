package com.jigpud.snow.controller.attraction;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Food;
import com.jigpud.snow.response.FoodResponse;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
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
public class AttractionFoodListController extends BaseController {
    private final AttractionService attractionService;
    private final FoodService foodService;

    @Autowired
    AttractionFoodListController(AttractionService attractionService, FoodService foodService) {
        this.attractionService = attractionService;
        this.foodService = foodService;
    }

    @PostMapping(PathConstant.GET_ATTRACTION_FOOD_LIST)
    ResponseBody<PageData<FoodResponse>> getAttractionFoodList(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage
    ) {
        log.debug("get attraction food list with pageSize: {}", pageSize);
        log.debug("get attraction food list with currentPage: {}", currentPage);
        if (attractionService.haveAttraction(attractionId)) {
            PageData<Food> foodList = attractionService.getFoodList(attractionId, pageSize, currentPage);
            PageData<FoodResponse> foodResponseList = PageData.fromPageData(foodList, food ->
                    FoodResponse.create(food, foodService));
            return Response.responseSuccess(foodResponseList);
        } else {
            log.debug("attraction {} not exists", attractionId);
            return Response.responseFailed("景点不存在！");
        }
    }
}
