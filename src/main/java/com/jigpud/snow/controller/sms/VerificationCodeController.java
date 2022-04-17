package com.jigpud.snow.controller.sms;

import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.sms.VerificationCodeService;
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
 * 获取短信验证码
 */
@Slf4j
@RestController
public class VerificationCodeController {
    private static final String TARGET_LOGIN = "login";
    private static final String TARGET_REGISTER = "register";
    private static final String TARGET_RETRIEVE_PASSWORD = "retrieve_password";

    private final UserService userService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    VerificationCodeController(UserService userService, VerificationCodeService verificationCodeService) {
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
    }

    @PostMapping(PathConstant.VERIFICATION_CODE)
    ResponseBody<?> verificationCode(
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username,
            @RequestParam(value = FormDataConstant.VERIFICATION_CODE_TARGET, required = false, defaultValue = TARGET_LOGIN) String target
    ) {
        if (username.isEmpty()) {
            log.debug("username need!");
            return Response.responseFailed("手机号不能为空！");
        }
        if ((TARGET_LOGIN.equals(target) || TARGET_RETRIEVE_PASSWORD.equals(target)) && !userService.haveUsernameIs(username)) {
            // 如果是登录和找回密码场景但用户名不存在就不发验证码了
            return Response.responseSuccess();
        }
        String verificationCode = verificationCodeService.newVerificationCode(username);
        boolean isSendSuccess = verificationCodeService.sendVerificationCode(username, verificationCode);
        if (isSendSuccess && verificationCodeService.verify(username, verificationCode)) {
            log.debug("send verification code success!");
            return Response.responseSuccess();
        }
        log.debug("send verification code failed!");
        return Response.responseFailed("请求短信验证码失败！");
    }
}
