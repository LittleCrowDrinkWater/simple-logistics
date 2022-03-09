package com.bolin.logistics.enums;

public enum PayEnum {

    ON_SITE_PAY(1,"待处理")
    ;


    private int type;
    private final String message;


    PayEnum(int type, String message) {
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
