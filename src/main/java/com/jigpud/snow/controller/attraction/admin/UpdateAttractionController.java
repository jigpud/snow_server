package com.jigpud.snow.controller.attraction.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.model.AttractionPicture;
import com.jigpud.snow.model.AttractionTag;
import com.jigpud.snow.model.Food;
import com.jigpud.snow.request.admin.UpdateAttractionRequest;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
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
public class UpdateAttractionController extends BaseController {
    private final AttractionService attractionService;
    private final TokenService tokenService;

    @Autowired
    UpdateAttractionController(AttractionService attractionService, TokenService tokenService) {
        this.attractionService = attractionService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.UPDATE_ATTRACTION)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> updateAttraction(
            @RequestBody UpdateAttractionRequest update,
            HttpServletRequest request
    ) {
        log.debug("ADMIN : update attraction: {}", update);
        String userid = tokenService.getUserid(getToken(request));
        if (update != null && attractionService.haveAttraction(update.getAttractionId())) {
            String attractionId = update.getAttractionId();
            Attraction attraction = attractionService.getAttraction(attractionId);

            // update name
            if (update.getName() != null) {
                attraction.setName(update.getName());
            } else {
                attraction.setName("");
            }

            // update description
            if (update.getDescription() != null) {
                attraction.setDescription(update.getDescription());
            } else {
                attraction.setDescription("");
            }

            // update tags
            List<String> oldTagList = attractionService.getTagList(attractionId, Long.MAX_VALUE, 1).getRecords().stream()
                    .map(AttractionTag::getTag)
                    .collect(Collectors.toList());
            List<String> newTagList = update.getTags();
            List<String> deleteTagList;
            List<String> addTagList = new ArrayList<>();
            if (newTagList != null) {
                deleteTagList = getDeleteList(oldTagList, newTagList);
                addTagList = getAddList(oldTagList, newTagList);
            } else {
                deleteTagList = oldTagList;
            }
            log.debug("tag list to delete {}", deleteTagList);
            log.debug("tag list to add {}", addTagList);
            deleteTagList.forEach(deleteTag -> attractionService.deleteTag(attractionId, deleteTag));
            addTagList.forEach(addTag -> attractionService.addTag(attractionId, addTag));

            // update location
            if (update.getLocation() != null) {
                attraction.setLocation(update.getLocation());
            } else {
                attraction.setLocation("");
            }

            // update pictures
            List<String> oldPictureList = attractionService.getPictureList(attractionId, Long.MAX_VALUE, 1).getRecords().stream()
                    .map(AttractionPicture::getPicture)
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
            log.debug("picture list to delete {}", deletePictureList);
            log.debug("picture list to add {}", addPictureList);
            deletePictureList.forEach(deletePicture -> attractionService.deletePicture(userid, deletePicture));
            addPictureList.forEach(addPicture -> attractionService.addPicture(attractionId, userid, addPicture));

            // update foods
            List<String> newFoodList = update.getFoods();
            List<String> oldFoodList = attractionService.getFoodList(attractionId, Long.MAX_VALUE, 1).getRecords().stream()
                    .map(Food::getFoodId)
                    .collect(Collectors.toList());
            List<String> deleteFoodList;
            List<String> addFoodList = new ArrayList<>();
            if (newFoodList != null) {
                deleteFoodList = getDeleteList(oldFoodList, newFoodList);
                addFoodList = getAddList(oldFoodList, newFoodList);
            } else {
                deleteFoodList = oldFoodList;
            }
            log.debug("food list to delete {}", deleteFoodList);
            log.debug("food list to add {}", addFoodList);
            deleteFoodList.forEach(deleteFood -> attractionService.deleteFood(attractionId, deleteFood));
            addFoodList.forEach(addFood -> attractionService.addFood(attractionId, addFood));

            attractionService.updateAttraction(attraction);

            log.debug("ADMIN : update attraction information success!");
            return Response.responseSuccess();
        }
        log.debug("ADMIN : update attraction information failed!");
        return Response.responseFailed("更新景点信息失败！");
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
