package com.bolin.logistics.utils;


import com.bolin.logistics.model.GoodsInfo;
import com.bolin.logistics.model.TransferInfo;

import java.util.UUID;

public class OrderNumGenUtil {

    public static String genGoodsInfoNo() {
        return "HY-" + UUID.randomUUID();
    }

    public static String genTransferInfoNo() {
        return "ZZ-" + UUID.randomUUID();
    }

    public static String genPayNo() {
        return "ZF-" + UUID.randomUUID();
    }

}
