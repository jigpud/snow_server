package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.PageData;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : jigpud
 * 获取用户列表
 */
@Slf4j
@RestController
public class UserListController extends BaseController {
    private final UserService userService;

    @Autowired
    UserListController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(PathConstant.GET_USER_LIST)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_READ)
    ResponseBody<PageData<User>> getUserList(
            @RequestParam(value = FormDataConstant.PAGE_COUNT, required = false, defaultValue = "0") Long pageCount,
            @RequestParam(value = FormDataConstant.PAGE, required = false, defaultValue = "0") Long page
    ) {
        log.debug("ADMIN : get user list with pageCount {}", pageCount);
        log.debug("ADMIN : get user list with page {}", page);
        return Response.responseSuccess(userService.users(pageCount, page));
    }
}
