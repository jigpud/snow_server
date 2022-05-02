package com.jigpud.snow.controller.food;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.FoodPicture;
import com.jigpud.snow.response.FoodPictureResponse;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.food.FoodService;
import com.jigpud.snow.service.user.UserService;
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
public class FoodPictureListController extends BaseController {
    private final FoodService foodService;
    private final UserService userService;

    @Autowired
    FoodPictureListController(FoodService foodService, UserService userService) {
        this.foodService = foodService;
        this.userService = userService;
    }

    @PostMapping(PathConstant.GET_FOOD_PICTURE_LIST)
    ResponseBody<PageData<FoodPictureResponse>> getFoodPictureList(
            @RequestParam(value = FormDataConstant.FOOD_ID, required = false, defaultValue = "") String foodId,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage
    ) {
        log.debug("get food picture list with pageSize: {}", pageSize);
        log.debug("get food picture list with currentPage: {}", currentPage);
        if (foodService.haveFood(foodId)) {
            PageData<FoodPicture> foodPictureList = foodService.getPictureList(foodId, pageSize, currentPage);
            PageData<FoodPictureResponse> foodPictureResponseList = PageData.fromPageData(foodPictureList, foodPicture ->
                    FoodPictureResponse.create(foodPicture, userService));
            return Response.responseSuccess(foodPictureResponseList);
        } else {
            log.debug("food {} not exists", foodId);
            return Response.responseFailed("美食不存在！");
        }
    }
}
