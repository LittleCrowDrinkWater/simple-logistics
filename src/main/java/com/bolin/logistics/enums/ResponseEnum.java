package com.bolin.logistics.enums;

public enum ResponseEnum {
    SUCCESS(2000,"请求成功!"),
    FAILED(4000,"请求失败!"),
    SUCCESS_ADD(2001,"新增成功!"),
    FAILED_ADD(4001,"新增失败!"),
    SUCCESS_UPDATE(2002,"更新成功!"),
    FAILED_UPDATE(4002,"更新失败!"),
    SUCCESS_DELETE(2003,"删除成功!"),
    FAILED_DELETE(4003,"删除失败!"),
    FAILED_QUERY(4004,"暂无数据!"),
    SUCCESS_LOGIN(2005,"登陆成功!"),
    FAILED_LOGIN(4005,"登录失败!")
    ;

    private int code;
    private String describe;

    ResponseEnum(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public int getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
