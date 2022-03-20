package com.jigpud.snow.controller;

import com.jigpud.snow.util.constant.HeaderConstant;
import com.jigpud.snow.util.response.Response;
import com.jigpud.snow.util.response.ResponseBody;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : jigpud
 * 异常处理
 */
public class BaseController {
    /**
     * shiro未授权错误处理
     * @return 未授权
     */
    @ExceptionHandler({UnauthorizedException.class})
    ResponseBody<?> handleUnauthorizedException() {
        return Response.response(403, "未授权！");
    }

    /**
     * shiro未认证错误处理
     * @return 未认证
     */
    @ExceptionHandler({UnauthenticatedException.class})
    ResponseBody<?> handleUnauthenticatedException() {
        return Response.response(401, "未认证！");
    }

    /**
     * 其它未知错误
     * @return internal error
     */
    @ExceptionHandler({Exception.class})
    ResponseBody<?> handleException() {
        return Response.response(500, "出错啦！");
    }

    protected String getToken(HttpServletRequest request) {
        return request.getHeader(HeaderConstant.AUTHORIZATION);
    }
}
