package com.jigpud.snow.controller.attraction;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.AttractionPicture;
import com.jigpud.snow.response.AttractionPictureResponse;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
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
public class AttractionPictureListController extends BaseController {
    private final AttractionService attractionService;
    private final UserService userService;

    @Autowired
    AttractionPictureListController(AttractionService attractionService, UserService userService) {
        this.attractionService = attractionService;
        this.userService = userService;
    }

    @PostMapping(PathConstant.GET_ATTRACTION_PICTURE_LIST)
    ResponseBody<PageData<AttractionPictureResponse>> getAttractionPictureList(
            @RequestParam(value = FormDataConstant.ATTRACTION_ID, required = false, defaultValue = "") String attractionId,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage
    ) {
        if (attractionService.haveAttraction(attractionId)) {
            log.debug("get attraction picture list with pageSize: {}", pageSize);
            log.debug("get attraction picture list with currentPage: {}", currentPage);
            PageData<AttractionPicture> attractionPictureList = attractionService.getPictureList(attractionId, pageSize, currentPage);
            PageData<AttractionPictureResponse> attractionPictureResponseList = PageData.fromPageData(attractionPictureList, attractionPicture ->
                    AttractionPictureResponse.create(attractionPicture, userService));
            return Response.responseSuccess(attractionPictureResponseList);
        } else {
            log.debug("attraction {} not exists!", attractionId);
            return Response.responseFailed("景点不存在！");
        }
    }
}
