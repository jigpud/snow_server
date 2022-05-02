package com.jigpud.snow.controller.food.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Food;
import com.jigpud.snow.model.FoodPicture;
import com.jigpud.snow.request.admin.UpdateFoodRequest;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.food.FoodService;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class UpdateFoodController extends BaseController {
    private final FoodService foodService;
    private final TokenService tokenService;

    @Autowired
    UpdateFoodController(FoodService foodService, TokenService tokenService) {
        this.foodService = foodService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.UPDATE_FOOD)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> updateFood(
            @RequestBody UpdateFoodRequest update,
            HttpServletRequest request
    ) {
        if (update != null && foodService.haveFood(update.getFoodId())) {
            String foodId = update.getFoodId();
            String userid = tokenService.getUserid(getToken(request));
            Food food = foodService.getFood(foodId);

            // update name
            if (update.getName() != null) {
                food.setName(update.getName());
            } else {
                food.setName("");
            }

            // update description
            if (update.getDescription() != null) {
                food.setDescription(update.getDescription());
            } else {
                food.setDescription("");
            }

            // update pictures
            List<String> oldPictureList = foodService.getPictureList(foodId, Long.MAX_VALUE, 1).getRecords().stream()
                    .map(FoodPicture::getPicture)
                    .collect(Collectors.toList());
            List<String> newPictureList = update.getPictures();
            List<String> deletePictureList;
            List<String> addPictureList = new ArrayList<>();
            if (newPictureList != null) {
                deletePictureList = getDeleteList(oldPictureList, newPictureList);
                addPictureList = getAddList(oldPictureList, newPictureList);
            } else {
                deletePictureList = oldPictureList;
            }
            deletePictureList.forEach(deletePicture -> foodService.deletePicture(foodId, deletePicture));
            addPictureList.forEach(addPicture -> foodService.addPicture(foodId, userid, addPicture));

            return Response.responseSuccess();
        } else {
            log.debug("update food failed");
            return Response.responseFailed("更新美食失败！");
        }
    }

    private List<String> getDeleteList(List<String> oldList, List<String> newList) {
        return oldList.stream()
                .filter(oldItem ->
                        newList.stream()
                                .noneMatch(oldItem::equals))
                .collect(Collectors.toList());
    }

    private List<String> getAddList(List<String> oldList, List<String> newList) {
        return newList.stream()
                .filter(newItem ->
                        oldList.stream()
                                .noneMatch(newItem::equals))
                .collect(Collectors.toList());
    }
}
