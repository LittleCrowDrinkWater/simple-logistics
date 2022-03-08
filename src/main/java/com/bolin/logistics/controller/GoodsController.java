package com.bolin.logistics.controller;


import com.bolin.logistics.model.GoodsInfo;
import com.bolin.logistics.service.GoodsService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@Api("货物单据控制器")
@RestController
@RequestMapping(("/goods"))
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "添加货物运单", notes = "添加货物运单", produces = "application/json")
    @PostMapping("/addGoods")
    public CustomResponse addGoods(@RequestBody GoodsInfo goodsInfo, @CookieValue("token") String token) {
        return goodsService.addGoodsInfo(token, goodsInfo);
    }
    @ApiOperation(value = "修改货物运单", notes = "修改货物运单", produces = "application/json")
    @PostMapping("/updateGoods")
    public CustomResponse updateGoods(@RequestBody GoodsInfo goodsInfo, @CookieValue("token") String token) {
        return goodsService.updateGoodsInfo(token, goodsInfo);
    }

    @ApiOperation(value = "删除货物运单", notes = "删除货物运单", produces = "application/json")
    @PostMapping("/deleteGoods")
    public CustomResponse deleteGoods(@CookieValue("token") String token , @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        long id = Long.parseLong(temp);
        return goodsService.deleteGoodsInfo(token, id);
    }
}