package com.bolin.logistics.enums;

public enum UserEnum {
    ADMIN(1 , "管理员"),
    OPERATOR(2 , "操作员"),
    DRIVER(3 , "司机"),
    CUSTOMER(4 , "客户")
    ;

    private int type;
    private String message;


    UserEnum(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType(){
        return type;
    }
    public String getMessage(){
        return message;
    }
}
