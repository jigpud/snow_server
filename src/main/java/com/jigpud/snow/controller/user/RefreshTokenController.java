package com.jigpud.snow.controller.user;

import com.jigpud.snow.controller.BaseController;
import com.jigpud.snow.service.token.TokenService;
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
 * token刷新
 */
@Slf4j
@RestController
public class RefreshTokenController extends BaseController {
    private final TokenService tokenService;

    @Autowired
    RefreshTokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(PathConstant.REFRESH_TOKEN)
    ResponseBody<?> refreshToken(
            @RequestParam(value = FormDataConstant.REFRESH_TOKEN, required = false, defaultValue = "") String refreshToken
    ) {
        if (refreshToken.isEmpty()) {
            log.debug("refresh token is empty!");
            return Response.responseFailed("refresh token不能为空！");
        }
        if (tokenService.verifyRefreshToken(refreshToken)) {
            String token = tokenService.refresh(refreshToken);
            if (tokenService.verify(token)) {
                // token刷新成功
                log.debug("the new token is {}", token);
                return Response.responseSuccess(new RefreshTokenResponse(token));
            }
        } else {
            // 登录已经过期
            log.debug("refresh token is expired!");
            return Response.response(401, "登录已过期！");
        }
        return Response.responseFailed("token刷新失败！");
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class RefreshTokenResponse {
        private String token;
    }
}
