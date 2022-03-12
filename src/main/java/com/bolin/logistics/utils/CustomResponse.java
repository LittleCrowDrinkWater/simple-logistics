package com.bolin.logistics.utils;

import com.bolin.logistics.enums.ResponseEnum;
import lombok.Data;

@Data
public class CustomResponse<T> {
    private int code;       //错误码
    private String message;    //呈现给用户的错误信息
    private T data;            //业务数据

    public CustomResponse(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getDescribe();
        this.data = data;
    }

    public CustomResponse(int code, String errorMessage) {
        this.code = code;
        this.message = errorMessage;
    }

    public static CustomResponse loginFailed() {
        return new CustomResponse(ResponseEnum.FAILED_LOGIN , null);
    }

    public static CustomResponse loginSuccess(Object object) {
        return new CustomResponse(ResponseEnum.SUCCESS_LOGIN, object);
    }

    public static CustomResponse success(Object object) {
        return new CustomResponse(ResponseEnum.SUCCESS, object);
    }

    public static CustomResponse success() {
        return new CustomResponse(ResponseEnum.SUCCESS, null);
    }

    public static CustomResponse fail() {
        return new CustomResponse(ResponseEnum.FAILED, null);
    }

    public static CustomResponse queryFailed() {
        return new CustomResponse(ResponseEnum.FAILED_QUERY, null);
    }

    public static CustomResponse addSuccess() {
        return new CustomResponse(ResponseEnum.SUCCESS_ADD, null);
    }

    public static CustomResponse addFailed() {
        return new CustomResponse(ResponseEnum.FAILED_ADD, null);
    }

    public static CustomResponse updateSuccess() {
        return new CustomResponse(ResponseEnum.SUCCESS_UPDATE, null);
    }

    public static CustomResponse updateFailed() {
        return new CustomResponse(ResponseEnum.FAILED_UPDATE, null);
    }

    public static CustomResponse deleteSuccess() {
        return new CustomResponse(ResponseEnum.SUCCESS_DELETE, null);
    }

    public static CustomResponse deleteFailed() {
        return new CustomResponse(ResponseEnum.FAILED_DELETE, null);
    }

}
