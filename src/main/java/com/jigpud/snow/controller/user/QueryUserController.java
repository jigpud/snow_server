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
    ResponseBody<PageData<User>> queryUser(
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username,
            @RequestParam(value = FormDataConstant.NICKNAME, required = false, defaultValue = "") String nickname,
            @RequestParam(value = FormDataConstant.PAGE_COUNT, required = false, defaultValue = "0") Long pageCount,
            @RequestParam(value = FormDataConstant.PAGE, required = false, defaultValue = "0") Long page
    ) {
        log.debug("ADMIN : query username {}", username);
        log.debug("ADMIN : query nickname {}", nickname);
        log.debug("ADMIN : query user with pageCount {}", pageCount);
        log.debug("ADMIN : query user with page {}", page);

        PageData<User> pageData = userService.usersUsernameAndNicknameLike(username, nickname, pageCount, page);
        return Response.responseSuccess(pageData);
    }
}
