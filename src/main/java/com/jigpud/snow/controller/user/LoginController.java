package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
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
            @RequestParam(FormDataConstant.USERNAME) String username,
            @RequestParam(FormDataConstant.PASSWORD) String password
    ) {
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

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class LoginResponse {
        private String token;
        private String refreshToken;
    }
}
