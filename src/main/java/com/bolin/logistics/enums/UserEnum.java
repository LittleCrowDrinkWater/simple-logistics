package com.bolin.logistics.enums;

public enum UserEnum {
    ADMIN(1 , "管理员"),
    DRIVER(2 , "司机"),
    CUSTOMER(3 , "客户"),
    OPERATOR(4 , "操作员"),
    FINANCE_STAFF(5 , "财务人员");

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
