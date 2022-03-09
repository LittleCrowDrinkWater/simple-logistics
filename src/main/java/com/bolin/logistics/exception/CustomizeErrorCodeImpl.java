package com.bolin.logistics.exception;

public enum CustomizeErrorCodeImpl implements CustomizeErrorCode {
    AUTHORIZE_FAIL(5001,"鉴定权限失败"),
    NO_LOGIN(5002,"当前操作需要登录,请登陆后重试"),
    SYS_ERROR(5003,"未知的系统错误"),
    USER_NOFOUND(5004,"查无此用户"),
    EXIST_TRANSFER_ORDER(5005,"存在转运单")
    ;

    CustomizeErrorCodeImpl(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
    private Integer code;
    private String message;


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
