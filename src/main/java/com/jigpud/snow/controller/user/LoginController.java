package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.response.LoginResponse;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.token.TokenService;
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
 * 使用用户名+密码登录
 */
@Slf4j
@RestController
public class LoginController extends BaseController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    LoginController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.LOGIN)
    ResponseBody<?> login(
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username,
            @RequestParam(value = FormDataConstant.PASSWORD, required = false, defaultValue = "") String password
    ) {
        if (username.isEmpty() || password.isEmpty()) {
            log.debug("username or password is empty!");
            return Response.responseFailed("用户名或密码不能为空！");
        }
        String refreshToken =  userService.login(username, password);
        String token = tokenService.createToken(refreshToken);
        log.debug("refreshToken: {}, token: {}", refreshToken, token);
        if (tokenService.verifyRefreshToken(refreshToken) && tokenService.verify(token)) {
            log.debug("login success!");
            return Response.responseSuccess(new LoginResponse(token, refreshToken));
        } else {
            log.debug("login failed!");
            return Response.responseFailed("用户名或密码错误！");
        }
    }
}
