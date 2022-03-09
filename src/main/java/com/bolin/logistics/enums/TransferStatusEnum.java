package com.bolin.logistics.enums;

public enum TransferStatusEnum {
    WAIT_OPERATION(1,"待处理"),
    NO_TRANSFER(2,"未转运"),
    TRANSFER(3,"存在转运"),
    ;


    private int type;
    private final String message;


    TransferStatusEnum(int type, String message) {
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
