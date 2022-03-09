package com.bolin.logistics.enums;



public enum LogisticsStatusEnum {
    WAIT_OPERATION(1,"待处理"),
    WAIT_PAYMENT(2,"等待支付"),
    IN_WAREHOUSE(3,"在仓库中，等待调配"),
    IN_TRANSIT(4,"运输中"),
    ARCHIVE(5,"完成存档")
    ;


    private int type;
    private final String message;


    LogisticsStatusEnum(int type, String message) {
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
