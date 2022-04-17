package com.jigpud.snow.controller.user;

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
 * 删除用户
 */
@Slf4j
@RestController
public class DeleteUserController extends BaseController {
    private final UserService userService;

    @Autowired
    DeleteUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(PathConstant.DELETE_USER)
    @RequiresRoles(RolesConstant.ADMIN)
    @RequiresPermissions(PermissionsConstant.ADMIN_WRITE)
    ResponseBody<?> deleteUser(
            @RequestParam(value = FormDataConstant.USERID, required = false, defaultValue = "") String userid,
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username
    ) {
        if (userid.isEmpty() && username.isEmpty()) {
            log.debug("userid or username need!");
            return Response.responseFailed("用户ID或用户名不能为空");
        }
        if (!userid.isEmpty()) {
            return deleteUserByUserid(userid);
        } else {
            return deleteUserByUsername(username);
        }
    }

    ResponseBody<?> deleteUserByUserid(String userid) {
        log.debug("delete user by userid {}", userid);
        if (userService.haveUseridIs(userid)) {
            userService.deleteUserByUserid(userid);
            if (!userService.haveUseridIs(userid)) {
                // 删除成功
                log.debug("ADMIN : delete user success!");
                return Response.responseSuccess();
            } else {
                // 删除失败
                log.debug("ADMIN : delete user failed!");
                return Response.responseFailed("删除失败！");
            }
        } else {
            // 用户不存在
            log.debug("ADMIN : user not exists!");
            return Response.responseFailed("用户不存在！");
        }
    }

    ResponseBody<?> deleteUserByUsername(String username) {
        log.debug("delete user by username {}", username);
        if (userService.haveUsernameIs(username)) {
            userService.deleteUserByUsername(username);
            if (!userService.haveUsernameIs(username)) {
                // 删除成功
                log.debug("ADMIN : delete user success!");
                return Response.responseSuccess();
            } else {
                // 删除失败
                log.debug("ADMIN : delete user failed!");
                return Response.responseFailed("删除失败！");
            }
        } else {
            // 用户不存在
            log.debug("ADMIN : user not exists!");
            return Response.responseFailed("用户不存在！");
        }
    }
}
