package com.jigpud.snow.util.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author : jigpud
 */
public class Response {
    public static final int RESPONSE_CODE_SUCCESS = 200;
    public static final int RESPONSE_CODE_FAILED = 400;

    public static final String RESPONSE_MESSAGE_SUCCESS = "success!";
    public static final String RESPONSE_MESSAGE_FAILED = "failed!";

    public static <T> ResponseBody<T> response(int code, String message, T data) {
        return new ResponseBody<>(code, message, data);
    }

    public static <T> ResponseBody<T> response(int code, String message) {
        return response(code, message, null);
    }

    public static <T> ResponseBody<T> responseSuccess(T data) {
        return response(RESPONSE_CODE_SUCCESS, RESPONSE_MESSAGE_SUCCESS, data);
    }

    public static <T> ResponseBody<T> responseSuccess() {
        return responseSuccess(null);
    }

    public static <T> ResponseBody<T> responseFailed(String message) {
        return response(RESPONSE_CODE_FAILED, message, null);
    }

    public static <T> ResponseBody<T> responseFailed() {
        return response(RESPONSE_CODE_FAILED, RESPONSE_MESSAGE_FAILED);
    }

    public static <T> PageData<T> pageData(Page<T> page) {
        PageData<T> pageData = new PageData<>();
        pageData.setRecords(page.getRecords());
        pageData.setPages(page.getPages());
        pageData.setCurrent(page.getCurrent());
        return pageData;
    }
}
