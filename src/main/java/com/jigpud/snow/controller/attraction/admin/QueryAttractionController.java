package com.jigpud.snow.controller.attraction.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.Attraction;
import com.jigpud.snow.response.AttractionResponse;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.attraction.AttractionService;
import com.jigpud.snow.service.follow.FollowService;
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
public class QueryAttractionController extends BaseController {
    private final AttractionService attractionService;
    private final TokenService tokenService;
    private final FollowService followService;

    @Autowired
    QueryAttractionController(
            AttractionService attractionService,
            TokenService tokenService,
            FollowService followService
    ) {
        this.attractionService = attractionService;
        this.tokenService = tokenService;
        this.followService = followService;
    }

    @PostMapping(PathConstant.QUERY_ATTRACTION)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_READ)
    ResponseBody<PageData<AttractionResponse>> queryAttraction(
            @RequestParam(value = FormDataConstant.NAME, required = false, defaultValue = "") String name,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage,
            HttpServletRequest request
    ) {
        log.debug("ADMIN : get attraction list with pageSize: {}", pageSize);
        log.debug("ADMIN : get attraction list with currentPage: {}", currentPage);
        String userid = tokenService.getUserid(getToken(request));
        PageData<Attraction> attractionList = attractionService.attractionsNameLike(name, pageSize, currentPage);
        PageData<AttractionResponse> attractionResponseList = PageData.fromPageData(attractionList, attraction ->
                AttractionResponse.create(attraction, userid, attractionService, followService));
        return Response.responseSuccess(attractionResponseList);
    }
}
