package com.jigpud.snow.controller.user;

import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.HeaderConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : jigpud
 */
@Slf4j
@Component("LogoutController")
@RestController
public class LogoutController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    LogoutController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping(PathConstant.USER_LOGOUT)
    ResponseBody<?> logout(HttpServletRequest request) {
        try {
            String token = request.getHeader(HeaderConstant.AUTHORIZATION).split(" ")[1];
            if (tokenService.verify(token)) {
                userService.logout(token);
                log.debug("logout success!");
                return Response.responseSuccess();
            }
        } catch (Exception ignored) {}
        log.debug("logout failed!");
        return Response.responseFailed("logout failed!");
    }
}
