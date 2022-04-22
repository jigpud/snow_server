package com.jigpud.snow.controller.user.admin;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
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
 * 创建用户
 */
@Slf4j
@RestController
public class CreateUserController extends BaseController {
    private final UserService userService;

    @Autowired
    CreateUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(PathConstant.CREATE_USER)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> createUser(
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username,
            @RequestParam(value = FormDataConstant.PASSWORD, required = false, defaultValue = "") String password
    ) {
        if (username.isEmpty() || password.isEmpty()) {
            // 需要同时提供用户名和密码
            return Response.responseFailed("both username adn password need!");
        }
        if (!userService.haveUsernameIs(username)) {
            userService.register(username, password);
            if (userService.haveUsernameIs(username)) {
                // 创建用户成功
                log.debug("ADMIN : create user success!");
                return Response.responseSuccess();
            } else {
                // 创建用户失败
                log.debug("ADMIN : create user failed!");
                return Response.responseFailed("创建用户失败");
            }
        } else {
            // 用户已存在
            log.debug("ADMIN : user already exists!");
            return Response.responseFailed("用户已存在！");
        }
    }
}
