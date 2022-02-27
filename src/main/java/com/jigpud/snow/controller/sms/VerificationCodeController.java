package com.jigpud.snow.controller.sms;

import com.jigpud.snow.service.sms.VerificationCodeService;
import com.jigpud.snow.util.constant.FormDataConstant;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
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
    private final VerificationCodeService verificationCodeService;

    @Autowired
    VerificationCodeController(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }

    @PostMapping(PathConstant.VERIFICATION_CODE)
    ResponseBody<?> verificationCode(@RequestParam(FormDataConstant.USERNAME) String username) {
        String verificationCode = verificationCodeService.newVerificationCode(username);
        boolean isSendSuccess = verificationCodeService.sendVerificationCode(username, verificationCode);
        if (isSendSuccess && verificationCodeService.verify(username, verificationCode)) {
            log.debug("send verification code success!");
            return Response.responseSuccess();
        } else {
            log.debug("send verification code failed!");
            return Response.responseFailed("请求短信验证码失败！");
        }
    }
}
