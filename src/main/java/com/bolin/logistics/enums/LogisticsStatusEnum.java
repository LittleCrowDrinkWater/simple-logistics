package com.bolin.logistics.enums;



public enum LogisticsStatusEnum {
    WAIT_OPERATION(1,"待处理"),
    IN_DELEVERY(2,"出库中"),
    IN_STORAGE(3,"入库中"),
    IN_WAREHOUSE(4,"在仓库中，等待调配"),
    IN_TRANSIT(5,"运输中"),
    ARCHIVE(6,"完成存档")
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
