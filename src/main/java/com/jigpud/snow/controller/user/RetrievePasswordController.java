package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
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
 * 找回密码
 */
@Slf4j
@RestController
public class RetrievePasswordController extends BaseController {
    private final UserService userService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    RetrievePasswordController(UserService userService, VerificationCodeService verificationCodeService) {
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
    }

    @PostMapping(PathConstant.RETRIEVE_PASSWORD)
    ResponseBody<?> retrievePassword(
            @RequestParam(value = FormDataConstant.USERNAME, required = false, defaultValue = "") String username,
            @RequestParam(value = FormDataConstant.PASSWORD, required = false, defaultValue = "") String password,
            @RequestParam(value = FormDataConstant.VERIFICATION_CODE, required = false, defaultValue = "") String verificationCode
    ) {
        if (username.isEmpty() || password.isEmpty() || verificationCode.isEmpty()) {
            log.debug("both username, password and verification code need!");
            return Response.responseFailed("用户名、密码或短信验证码不能为空！");
        }
        if (verificationCodeService.verify(username, verificationCode)) {
            String userid = userService.getUserid(username);
            User oldUser = userService.getUserByUserid(userid);
            userService.updatePassword(userid, password);
            User newUser = userService.getUserByUserid(userid);
            if (oldUser != null && oldUser.getPassword() != null && newUser != null && newUser.getPassword() != null) {
                if (!oldUser.getPassword().equals(newUser.getPassword())) {
                    // 找回密码成功
                    log.debug("retrieve password success!");
                    return Response.responseSuccess();
                }
            }
        } else {
            log.debug("verification code is illegal!");
            return Response.responseFailed("短信验证码错误！");
        }
        return Response.responseFailed("找回密码失败！");
    }
}
