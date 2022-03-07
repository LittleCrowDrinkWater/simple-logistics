package com.bolin.logistics.exception;

public class CustomizeException extends RuntimeException{
    private String message ;
    private Integer code;

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
