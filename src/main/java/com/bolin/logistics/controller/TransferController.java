package com.bolin.logistics.controller;

import com.bolin.logistics.model.GoodsInfo;
import com.bolin.logistics.model.TransferInfo;
import com.bolin.logistics.service.GoodsService;
import com.bolin.logistics.service.TransferService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Api("中转单据控制器")
@RestController
@RequestMapping(("/transfer"))
public class TransferController {

    @Autowired
    private TransferService transferService;

    @ApiOperation(value = "添加中转运单", notes = "添加中转运单", produces = "application/json")
    @PostMapping("/addTransfer")
    public CustomResponse addGoods(@RequestBody TransferInfo transferInfo, @CookieValue("token") String token) {
        return transferService.addTransferInfo(token, transferInfo);
    }

    @ApiOperation(value = "修改中转运单", notes = "修改中转运单", produces = "application/json")
    @PostMapping("/updateTransfer")
    public CustomResponse updateGoods(@RequestBody TransferInfo transferInfo, @CookieValue("token") String token) {
        return transferService.updateTransferInfo(token, transferInfo);
    }

    @ApiOperation(value = "删除中转运单", notes = "删除中转运单", produces = "application/json")
    @PostMapping("/deleteTransfer")
    public CustomResponse deleteGoods(@CookieValue("token") String token, @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        long id = Long.parseLong(temp);
        return transferService.deleteTransferInfo(token, id);
    }
}