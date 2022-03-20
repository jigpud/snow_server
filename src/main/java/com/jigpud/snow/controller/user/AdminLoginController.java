package com.jigpud.snow.controller.user;

import com.jigpud.snow.service.role.RoleService;
import com.jigpud.snow.service.sms.VerificationCodeService;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : jigpud
 * 后台管理系统登录
 */
@Slf4j
@RestController
public class AdminLoginController {
    private final UserService userService;
    private final TokenService tokenService;
    private final RoleService roleService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    AdminLoginController(
            UserService userService,
            TokenService tokenService,
            RoleService roleService,
            VerificationCodeService verificationCodeService
    ) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.roleService = roleService;
        this.verificationCodeService = verificationCodeService;
    }

    @PostMapping(PathConstant.ADMIN_LOGIN)
    ResponseBody<AdminLoginResponse> login(
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username,
            @RequestParam(value = FormDataConstant.VERIFICATION_CODE, required = false, defaultValue = "") String verificationCode
    ) {
        if (username.isEmpty() || verificationCode.isEmpty()) {
            log.debug("both username and verification code need!");
            return Response.responseFailed("用户名或短信验证码不能为空！");
        }
        if (verificationCodeService.verify(username, verificationCode)) {
            if (userService.haveUsernameIs(username)) {
                String userid = userService.getUserid(username);
                if (roleService.haveRoles(userid, RolesConstant.ADMIN)) {
                    String refreshToken = tokenService.createRefreshToken(userid);
                    String token = tokenService.createToken(refreshToken);
                    log.debug("admin login success!");
                    return Response.responseSuccess(new AdminLoginResponse(refreshToken, token));
                } else {
                    log.debug("user not admin!");
                    return Response.response(401,"用户不是管理员！");
                }
            }
        } else {
            log.debug("verification code illegal!");
            return Response.responseFailed("短信验证码错误！");
        }
        log.debug("admin login failed!");
        return Response.responseFailed("登录失败！");
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class AdminLoginResponse {
        private String refreshToken;
        private String token;
    }
}
