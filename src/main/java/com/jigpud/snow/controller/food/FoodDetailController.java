package com.jigpud.snow.controller.food;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Food;
import com.jigpud.snow.response.FoodResponse;
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
public class FoodDetailController extends BaseController {
    private final FoodService foodService;

    @Autowired
    FoodDetailController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping(PathConstant.GET_FOOD)
    ResponseBody<FoodResponse> getFoodDetail(
            @RequestParam(value = FormDataConstant.FOOD_ID, required = false, defaultValue = "") String foodId
    ) {
        if (foodService.haveFood(foodId)) {
            Food food = foodService.getFood(foodId);
            FoodResponse foodResponse = FoodResponse.create(food, foodService);
            return Response.responseSuccess(foodResponse);
        } else {
            log.debug("food {} not exists", foodId);
            return Response.responseFailed("美食不存在！");
        }
    }
}
