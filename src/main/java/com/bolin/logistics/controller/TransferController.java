package com.bolin.logistics.controller;

import com.bolin.logistics.model.TransferInfo;
import com.bolin.logistics.service.TransferService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Api(tags = "中转单据控制器")
@RestController
@RequestMapping(("/transfer"))
public class TransferController {

    @Autowired
    private TransferService transferService;

    @ApiOperation(value = "添加中转运单", notes = "添加中转运单,传入TransferInfo对象，不需要id，其余字段均需要", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "新增成功"),
            @ApiResponse(code = 4001, message = "新增失败")
    })
    @PostMapping("/addTransfer")
    public CustomResponse addGoods(@RequestBody TransferInfo transferInfo, HttpServletRequest request) {
        return transferService.addTransferInfo(request, transferInfo);
    }

    @ApiOperation(value = "出库", notes = "修改中转运单,传入id和需要修改的字段,无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/deliver/{id}")
    public CustomResponse updateGoods(@PathVariable("id") String id, HttpServletRequest request) {
        return transferService.deliver(id, request);
    }

    @ApiOperation(value = "司机接货", notes = "修改中转运单,传入id和需要修改的字段,无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/driverAccept/{id}")
    public CustomResponse driverAccept(@PathVariable("id") String id, HttpServletRequest request) {
        return transferService.driverAccept(id, request);
    }

    @ApiOperation(value = "司机抵达", notes = "修改中转运单,传入id和需要修改的字段,无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/driverArrive/{id}")
    public CustomResponse driverArrive(@PathVariable("id") String id, HttpServletRequest request) {
        return transferService.driverArrive(id, request);
    }

    @ApiOperation(value = "入库", notes = "修改中转运单,传入id和需要修改的字段,无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/storage/{id}")
    public CustomResponse storage(@PathVariable("id") String id, HttpServletRequest request) {
        return transferService.storage(id, request);
    }

    @ApiOperation(value = "删除中转运单", notes = "删除中转运单时,传入\"id\":id，后端封装在map里读取", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "删除成功"),
            @ApiResponse(code = 4003, message = "删除失败")
    })
    @PostMapping("/deleteTransfer")
    public CustomResponse deleteGoods(HttpServletRequest request, @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        long id = Long.parseLong(temp);
        return transferService.deleteTransferInfo(request, id);
    }
}