package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.permission.PermissionService;
import com.jigpud.snow.service.role.RoleService;
import com.jigpud.snow.service.sms.VerificationCodeService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : jigpud
 * 注册
 */
@Slf4j
@RestController
public class RegisterController extends BaseController {
    private final UserService userService;
    private final VerificationCodeService verificationCodeService;
    private final PermissionService permissionService;
    private final RoleService roleService;

    @Autowired
    RegisterController(
            UserService userService,
            VerificationCodeService verificationCodeService,
            PermissionService permissionService,
            RoleService roleService
    ) {
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
        this.permissionService = permissionService;
        this.roleService = roleService;
    }

    @PostMapping(PathConstant.REGISTER)
    ResponseBody<?> register(
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username,
            @RequestParam(value = FormDataConstant.PASSWORD, required = false, defaultValue = "") String password,
            @RequestParam(value = FormDataConstant.VERIFICATION_CODE, required = false, defaultValue = "") String verificationCode
    ) {
        if (username.isEmpty() || password.isEmpty() || verificationCode.isEmpty()) {
            log.debug("username, password or verification code is empty!");
            return Response.responseFailed("用户名、密码或短信验证码不能为空！");
        }
        if (verificationCodeService.verify(username, verificationCode)) {
            if (!userService.haveUsernameIs(username)) {
                userService.register(username, password);
                if (userService.haveUsernameIs(username)) {
                    // 注册成功
                    log.debug("register success!");

                    // 添加角色和权限
                    String userid = userService.getUserid(username);
                    roleService.addRoles(userid, RolesConstant.USER);
                    permissionService.grantPermissions(userid,
                            PermissionsConstant.USER_READ,
                            PermissionsConstant.USER_WRITE);

                    return Response.responseSuccess();
                } else {
                    // 注册失败
                    log.debug("register failed!");
                    return Response.responseFailed();
                }
            } else {
                // 手机号已注册，销毁验证码
                log.debug("user already register!");
                verificationCodeService.expire(username, verificationCode);
                return Response.responseFailed("该手机已注册！");
            }
        } else {
            // 验证码错误
            log.debug("verification code illegal!");
            return Response.responseFailed("短信验证码错误！");
        }
    }
}
