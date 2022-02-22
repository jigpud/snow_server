package com.jigpud.snow.controller.admin.user;

import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.HeaderConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author jigpud
 * admin登录
 */
@Slf4j
@Component("AdminLoginController")
@RestController
public class LoginController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    LoginController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.ADMIN_LOGIN)
    ResponseBody<?> login(
            @RequestParam(FormDataConstant.USERNAME) String username,
            @RequestParam(FormDataConstant.PASSWORD) String password,
            HttpServletResponse response
    ) {
        String token = userService.adminLogin(username, password);
        if (tokenService.verify(token)) {
            log.debug("login success!");
            response.addHeader(HeaderConstant.AUTHORIZATION, "Bearer " + token);
            return Response.responseSuccess();
        } else {
            log.debug("login failed!");
            return Response.responseFailed("login failed!");
        }
    }
}
