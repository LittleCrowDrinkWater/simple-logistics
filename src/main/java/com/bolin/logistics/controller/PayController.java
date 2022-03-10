package com.bolin.logistics.controller;

import com.bolin.logistics.model.Pay;
import com.bolin.logistics.service.PayService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "支付单据控制器")
@RestController
@RequestMapping(("/pay"))
public class PayController {

    @Autowired
    private PayService payService;

    @ApiOperation(value = "添加支付单", notes = "添加支付单,传入TransferInfo对象，不需要id，其余字段均需要", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "新增成功"),
            @ApiResponse(code = 4001, message = "新增失败")
    })
    @PostMapping("/addPay")
    public CustomResponse addPay(@RequestBody Pay pay, HttpServletRequest request) {
        return payService.addPay(request, pay);
    }

    @ApiOperation(value = "修改支付单", notes = "修改支付单,传入id和需要修改的字段,无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/updatePay")
    public CustomResponse updatePay(@RequestBody Pay pay, HttpServletRequest request) {
        return payService.updatePay(request, pay);
    }

    @ApiOperation(value = "删除支付单", notes = "删除支付单时,传入\"id\":id，后端封装在map里读取", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "删除成功"),
            @ApiResponse(code = 4003, message = "删除失败")
    })
    @PostMapping("/deletePay")
    public CustomResponse deletePay(HttpServletRequest request, @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        long id = Long.parseLong(temp);
        return payService.deletePay(request, id);
    }

    @ApiOperation(value = "查询所有支付单，仅管理员可用", notes = "查询所有支付单，仅管理员可用")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "请求成功"),
            @ApiResponse(code = 4000, message = "请求失败")
    })
    @GetMapping("/list")
    public CustomResponse list(@RequestParam int page, @RequestParam int size, HttpServletRequest request) {
        return payService.list(page, size, request);
    }
}