package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.service.sms.VerificationCodeService;
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
 * 短信验证码登陆
 */
@Slf4j
@RestController
public class LoginWithVerificationCodeController extends BaseController {
    private final VerificationCodeService verificationCodeService;
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    LoginWithVerificationCodeController(
            VerificationCodeService verificationCodeService,
            UserService userService,
            TokenService tokenService
    ) {
        this.verificationCodeService = verificationCodeService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.LOGIN_WITH_VERIFICATION_CODE)
    ResponseBody<LoginResponse> login(
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username,
            @RequestParam(value = FormDataConstant.VERIFICATION_CODE, required = false, defaultValue = "") String verificationCode
    ) {
        if (username.isEmpty() || verificationCode.isEmpty()) {
            log.debug("both username and verification code need!");
            return Response.responseFailed("用户名或短信验证不能为空！");
        }
        if (!userService.haveUsernameIs(username)) {
            log.debug("username: {} not exists!", username);
            return Response.responseFailed("用户名不存在！");
        }
        if (verificationCodeService.verify(username, verificationCode)) {
            String userid = userService.getUserid(username);
            String refreshToken = tokenService.createRefreshToken(userid);
            String token = tokenService.createToken(refreshToken);
            if (tokenService.verifyRefreshToken(refreshToken) && tokenService.verify(token)) {
                // 登陆成功
                log.debug("login success!");
                return Response.responseSuccess(new LoginResponse(token, refreshToken));
            }
        } else {
            // 短信验证码错误
            log.debug("verification code illegal!");
            return Response.responseFailed("短信验证码错误！");
        }
        return Response.responseFailed("登陆失败！");
    }

    @NoArgsConstructor
    @AllArgsConstructor@Data
    static class LoginResponse {
        private String token;
        private String refreshToken;
    }
}
