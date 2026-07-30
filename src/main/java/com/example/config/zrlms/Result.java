package com.example.config.zrlms;

/**
 * 统一返回结果封装类
 */
public class Result<T> {

    private Integer code;      // 状态码：0成功，非0失败
    private String message;    // 提示信息
    private T data;            // 数据（泛型）

    // 构造方法私有，通过静态方法创建
    private Result() {}

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // ========== 成功 ==========
    public static <T> Result<T> success() {
        return new Result<>(0, "操作成功", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(0, "操作成功", data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(0, message, data);
    }

    // ========== 失败 ==========
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    // ========== Getter/Setter ==========
    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}