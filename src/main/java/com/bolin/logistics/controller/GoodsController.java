package com.bolin.logistics.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bolin.logistics.model.GoodsInfo;
import com.bolin.logistics.model.Pay;
import com.bolin.logistics.service.GoodsService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Api(tags = "货物单据控制器")
@RestController
@RequestMapping(("/goods"))
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "添加货物运单", notes = "添加货物运单,GoodsInfo以及Pay,不需要id，传参格式为{\n" +
            "    \"GoodsInfo\": {\n" +
            "        \"operateUserId\": 74\n" +
            "    },\n" +
            "    \"Pay\": {\n" +
            "        \"paymentNo\": \"fake_data\"\n" +
            "    }\n" +
            "}", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "新增成功"),
            @ApiResponse(code = 4001, message = "新增失败")
    })
    @PostMapping("/addGoods")
    public CustomResponse addGoods(@RequestBody String str, @CookieValue("token") String token) {
        JSONObject jsonObject = JSONArray.parseObject(str);
        String jsonOfGoodsInfo = jsonObject.get("GoodsInfo").toString();
        String jsonOfPay = jsonObject.get("Pay").toString();
        GoodsInfo goodsInfo = JSONObject.parseObject(jsonOfGoodsInfo, GoodsInfo.class);
        Pay pay = JSON.parseObject(jsonOfPay, Pay.class);
        return goodsService.addGoodsInfo(token, goodsInfo, pay);
    }

    @ApiOperation(value = "发货，仅操作员和管理员可用", notes = "发货,传入id和需要修改的字段,无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/deliver/{id}")
    public CustomResponse deliver(@PathVariable("id") String id, @CookieValue("token") String token) {
        return goodsService.deliver(id, token);
    }

    @ApiOperation(value = "修改货物运单状态，仅司机接货", notes = "修改货物运单,传入GoodsInfo的id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/driverAccept/{id}")
    public CustomResponse driverAccept(@PathVariable("id") String id, @CookieValue("token") String token) {
        return goodsService.driverAccept(id, token);
    }

    @ApiOperation(value = "修改货物运单状态，仅司机抵达", notes = "修改货物运单,传入GoodsInfo的id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/driverArrive/{id}")
    public CustomResponse driverArrive(@PathVariable("id") String id, @CookieValue("token") String token) {
        return goodsService.driverArrive(id, token);
    }

    @ApiOperation(value = "入库", notes = "修改货物运单,传入GoodsInfo的id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/storage/{id}")
    public CustomResponse storage(@PathVariable("id") String id, @CookieValue("token") String token) {
        return goodsService.storage(id, token);
    }

    @ApiOperation(value = "收货", notes = "修改货物运单,传入GoodsInfo的id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/receive/{id}")
    public CustomResponse receive(@PathVariable("id") String id, @CookieValue("token") String token) {
        return goodsService.receive(id, token);
    }


    @ApiOperation(value = "删除货物运单", notes = "删除货物运单,传入\"id\":id，后端封装在map里读取", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "删除成功"),
            @ApiResponse(code = 4003, message = "删除失败")
    })
    @PostMapping("/deleteGoods")
    public CustomResponse deleteGoods(@CookieValue("token") String token, @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        long id = Long.parseLong(temp);
        return goodsService.deleteGoodsInfo(token, id);
    }
}