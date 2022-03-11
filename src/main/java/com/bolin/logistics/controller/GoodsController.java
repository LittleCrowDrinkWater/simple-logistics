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
import javax.servlet.http.HttpServletRequest;

@Api(tags = "货物单据控制器")
@RestController
@RequestMapping(("/goods"))
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "添加货物运单", notes = "添加货物运单,GoodsInfo以及Pay,不需要id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "新增成功"),
            @ApiResponse(code = 4001, message = "新增失败")
    })
    @PostMapping("/addGoods")
    public CustomResponse addGoods(@RequestBody GoodsInfo goodsInfo, HttpServletRequest request) {
        return goodsService.addGoodsInfo(request, goodsInfo);
    }

    @ApiOperation(value = "发货，仅操作员和管理员可用", notes = "发货,传入id和需要修改的字段,无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @GetMapping("/deliver/{id}")
    public CustomResponse deliver( @PathVariable("id") String id, HttpServletRequest request) {
        return goodsService.deliver(id, request);
    }

    @ApiOperation(value = "修改货物运单状态，仅司机接货", notes = "修改货物运单,传入GoodsInfo的id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @GetMapping("/driverAccept/{id}")
    public CustomResponse driverAccept(@PathVariable("id") String id, HttpServletRequest request) {
        return goodsService.driverAccept(id, request);
    }

    @ApiOperation(value = "修改货物运单状态，仅司机抵达", notes = "修改货物运单,传入GoodsInfo的id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @GetMapping("/driverArrive/{id}")
    public CustomResponse driverArrive(@PathVariable("id") String id, HttpServletRequest request) {
        return goodsService.driverArrive(id, request);
    }

    @ApiOperation(value = "入库", notes = "修改货物运单,传入GoodsInfo的id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @GetMapping("/storage/{id}")
    public CustomResponse storage(@PathVariable("id") String id, HttpServletRequest request) {
        return goodsService.storage(id, request);
    }

    @ApiOperation(value = "收货", notes = "修改货物运单,传入GoodsInfo的id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @GetMapping("/receive/{id}")
    public CustomResponse receive(@PathVariable("id") String id, HttpServletRequest request) {
        return goodsService.receive(id, request);
    }


    @ApiOperation(value = "删除货物运单", notes = "删除货物运单,Get请求添加参数id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "删除成功"),
            @ApiResponse(code = 4003, message = "删除失败")
    })
    @GetMapping("/deleteGoods/{id}")
    public CustomResponse deleteGoods(@PathVariable("id") String id , HttpServletRequest request) {
        return goodsService.deleteGoodsInfo(request, Long.parseLong(id));
    }

    @ApiOperation(value = "按照类型查询货运单", notes = "按照类型查询货运单，对于ADMIN，查询所有货运单，对于OPERATOR，查询其负责的所有活跃的货运单，" +
            "对于DRIVER,查询其负责的所有活跃的货运单，对于customer，查询所有其是发货或收货人的货运单")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "请求成功"),
            @ApiResponse(code = 4000, message = "请求失败")
    })
    @GetMapping("/list")
    public CustomResponse list(@RequestParam int page, @RequestParam int size , HttpServletRequest request) {
        return goodsService.list( page, size, request);
    }
}