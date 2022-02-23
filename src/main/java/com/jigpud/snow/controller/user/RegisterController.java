package com.jigpud.snow.controller.user;

import com.jigpud.snow.service.sms.VerificationCodeService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : jigpud
 * 注册
 */
@RestController
public class RegisterController {
    private final UserService userService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    RegisterController(UserService userService, VerificationCodeService verificationCodeService) {
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
    }

    @PostMapping(PathConstant.VERIFICATION_CODE)
    ResponseBody<?> verificationCode(
            @RequestParam(FormDataConstant.USERNAME) String username
    ) {
        String verificationCode = verificationCodeService.newVerificationCode(username);
        boolean isSendSuccess = verificationCodeService.sendVerificationCode(username, verificationCode);
        if (isSendSuccess && verificationCodeService.verify(username, verificationCode)) {
            return Response.responseSuccess();
        } else {
            return Response.responseFailed("请求短信验证码失败！");
        }
    }

    @PostMapping(PathConstant.REGISTER)
    ResponseBody<?> register(
            @RequestParam(FormDataConstant.USERNAME) String username,
            @RequestParam(FormDataConstant.PASSWORD) String password,
            @RequestParam(FormDataConstant.VERIFICATION_CODE) String verificationCode
    ) {
        if (verificationCodeService.verify(username, verificationCode)) {
            if (!userService.hasRegistered(username)) {
                userService.register(username, password);
                if (userService.hasRegistered(username)) {
                    // 注册成功
                    return Response.responseSuccess();
                } else {
                    // 注册失败
                    return Response.responseFailed();
                }
            } else {
                // 手机号已注册，销毁验证码
                verificationCodeService.expire(username, verificationCode);
                return Response.responseFailed("该手机已注册！");
            }
        } else {
            // 验证码错误
            return Response.responseFailed("短信验证码错误！");
        }
    }
}
