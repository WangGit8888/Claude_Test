package com.example.common.model;

import lombok.Data;

@Data
public class ApiResponseBody<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ApiResponseBody<T> success() {
        return success(null);
    }

    public static <T> ApiResponseBody<T> success(T data) {
        ApiResponseBody<T> body = new ApiResponseBody<>();
        body.setCode(200);
        body.setMessage("success");
        body.setData(data);
        return body;
    }

    public static <T> ApiResponseBody<T> error(int code, String message) {
        ApiResponseBody<T> body = new ApiResponseBody<>();
        body.setCode(code);
        body.setMessage(message);
        return body;
    }

    public static <T> ApiResponseBody<T> error(String message) {
        return error(500, message);
    }
}
