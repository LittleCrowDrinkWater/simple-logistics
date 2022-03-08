package com.bolin.logistics.controller;


import com.bolin.logistics.model.GoodsInfo;
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

    @ApiOperation(value = "添加货物运单", notes = "添加货物运单,GoodsInfo,不需要id，其余字段均需要", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "新增成功"),
            @ApiResponse(code = 4001, message = "新增失败")
    })
    @PostMapping("/addGoods")
    public CustomResponse addGoods(@RequestBody GoodsInfo goodsInfo, @CookieValue("token") String token) {
        return goodsService.addGoodsInfo(token, goodsInfo);
    }
    @ApiOperation(value = "修改货物运单", notes = "修改货物运单,传入id和需要修改的字段,无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/updateGoods")
    public CustomResponse updateGoods(@RequestBody GoodsInfo goodsInfo, @CookieValue("token") String token) {
        return goodsService.updateGoodsInfo(token, goodsInfo);
    }

    @ApiOperation(value = "删除货物运单", notes = "删除货物运单,传入\"id\":id，后端封装在map里读取", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "删除成功"),
            @ApiResponse(code = 4003, message = "删除失败")
    })
    @PostMapping("/deleteGoods")
    public CustomResponse deleteGoods(@CookieValue("token") String token , @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        long id = Long.parseLong(temp);
        return goodsService.deleteGoodsInfo(token, id);
    }
}