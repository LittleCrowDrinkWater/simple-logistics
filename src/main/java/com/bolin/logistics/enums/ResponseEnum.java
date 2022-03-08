package com.bolin.logistics.enums;

public enum ResponseEnum {
    SUCCESS(0,"请求成功!"),
    SUCCESS_ADD(0,"新增成功!"),
    FAILED_ADD(0,"新增失败!"),
    SUCCESS_UPDATE(0,"更新成功!"),
    FAILED_UPDATE(0,"更新失败!"),
    SUCCESS_DELETE(0,"删除成功!"),
    FAILED_DELETE(0,"删除失败!"),
    FAILED_QUERY(0,"暂无数据!"),
    FAILED_REVOKE(0,"撤销失败!"),
    SUCCESS_REVOKE(0,"撤销成功!"),
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
