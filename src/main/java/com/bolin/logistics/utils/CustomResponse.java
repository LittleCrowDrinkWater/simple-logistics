package com.bolin.logistics.utils;

import com.bolin.logistics.enums.ResponseEnum;
import lombok.Data;

@Data
public class CustomResponse<T> {
    private int errorCode;       //错误码
    private String errorMessage;    //呈现给用户的错误信息
    private T data;            //业务数据

    public CustomResponse(ResponseEnum responseEnum, T data) {
        this.errorCode = responseEnum.getCode();
        this.errorMessage = responseEnum.getDescribe();
        this.data = data;
    }

    public CustomResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static CustomResponse build(ResponseEnum responseEnum, Object object) {
        return new CustomResponse(responseEnum, object);
    }

    public static CustomResponse success(Object object) {
        return new CustomResponse(ResponseEnum.SUCCESS, object);
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
