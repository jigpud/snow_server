package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.model.User;
import com.jigpud.snow.request.UpdateUserInformationRequest;
import com.jigpud.snow.response.ResponseBody;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.service.user.UserService;
import com.jigpud.snow.util.constant.PathConstant;
import com.jigpud.snow.util.constant.PermissionsConstant;
import com.jigpud.snow.util.constant.RolesConstant;
import com.jigpud.snow.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : jigpud
 */
@Slf4j
@RestController
public class UpdateInformationController extends BaseController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    UpdateInformationController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.UPDATE_INFORMATION)
    @RequiresRoles(RolesConstant.USER)
    @RequiresPermissions(PermissionsConstant.USER_WRITE)
    ResponseBody<?> updateInformation(
            @RequestBody UpdateUserInformationRequest updateInformationRequest,
            HttpServletRequest request
    ) {
        String token = getToken(request);
        String userid = tokenService.getUserid(token);
        User user = userService.getUserByUserid(userid);
        log.debug("updateInformationRequest: {}", updateInformationRequest);

        if (user != null) {
            // update nickname
            if (updateInformationRequest.getNickname() != null) {
                user.setNickname(updateInformationRequest.getNickname());
            }

            // update gender
            if (updateInformationRequest.getGender() != null) {
                user.setGender(updateInformationRequest.getGender());
            }

            // update age
            if (updateInformationRequest.getAge() != null) {
                user.setAge(updateInformationRequest.getAge());
            }

            // update signature
            if (updateInformationRequest.getSignature() != null) {
                user.setSignature(updateInformationRequest.getSignature());
            }

            // update avatar
            if (updateInformationRequest.getAvatar() != null) {
                user.setAvatar(updateInformationRequest.getAvatar());
            }

            // update background
            if (updateInformationRequest.getBackground() != null) {
                user.setBackground(updateInformationRequest.getBackground());
            }

            userService.update(user);
            return Response.responseSuccess();
        }
        return Response.responseFailed("???????????????????????????");
    }
}
