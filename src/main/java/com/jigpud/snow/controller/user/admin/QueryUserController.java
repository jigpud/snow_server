package com.jigpud.snow.controller.user.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.response.UserResponse;
import com.jigpud.snow.service.user.UserService;
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
public class QueryUserController extends BaseController {
    private final UserService userService;

    @Autowired
    QueryUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(PathConstant.QUERY_USER)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_READ)
    ResponseBody<PageData<UserResponse>> queryUser(
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username,
            @RequestParam(value = FormDataConstant.NICKNAME, required = false, defaultValue = "") String nickname,
            @RequestParam(value = FormDataConstant.PAGE_SIZE, required = false, defaultValue = "0") Long pageSize,
            @RequestParam(value = FormDataConstant.CURRENT_PAGE, required = false, defaultValue = "0") Long currentPage
    ) {
        log.debug("ADMIN : query username {}", username);
        log.debug("ADMIN : query nickname {}", nickname);
        log.debug("ADMIN : query user with pageSize {}", pageSize);
        log.debug("ADMIN : query user with currentPage {}", currentPage);
        PageData<User> userList = userService.usersUsernameAndNicknameLike(username, nickname, pageSize, currentPage);
        PageData<UserResponse> userInformationResponseList = PageData.fromPageData(userList, UserResponse::create);
        return Response.responseSuccess(userInformationResponseList);
    }
}
