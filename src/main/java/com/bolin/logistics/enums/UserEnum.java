package com.bolin.logistics.enums;

public enum UserEnum {
    ADMIN(0 , "管理员"),
    DRIVER(1 , "司机"),
    OPERATOR(3 , "操作员"),
    CUSTOMER(4 , "客户");

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
