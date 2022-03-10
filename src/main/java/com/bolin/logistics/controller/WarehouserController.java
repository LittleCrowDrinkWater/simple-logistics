package com.bolin.logistics.controller;

import com.bolin.logistics.model.Warehouse;
import com.bolin.logistics.service.WarehouserService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "仓库地址关联相关控制器")
@RestController
@RequestMapping(("/Warehouse"))
public class WarehouserController {

    @Autowired
    private WarehouserService warehouserService;

    @ApiOperation(value = "添加仓库关联", notes = "前端传入User对象时，不需要id，description可选，其余字段均需要", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "新增成功"),
            @ApiResponse(code = 4001, message = "新增失败")
    })
    @PostMapping("/addWarehouse")
    public CustomResponse addWarehouse(@RequestBody Warehouse warehouse, HttpServletRequest request) {
        return warehouserService.addWarehouse(request, warehouse);
    }

    @ApiOperation(value = "修改仓库关联", notes = "修改仓库关联时传入id和需要修改的字段，无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "修改成功"),
            @ApiResponse(code = 4002, message = "修改失败")
    })
    @PostMapping("/updateWarehouse")
    public CustomResponse updateWarehouse(@RequestBody Warehouse warehouse, HttpServletRequest request) {
        return warehouserService.updateLocation(request, warehouse);
    }

    @ApiOperation(value = "删除仓库关联", notes = "删除仓库关联时，传入\"id\":id，后端封装在map里读取", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "删除成功"),
            @ApiResponse(code = 4003, message = "删除失败")
    })
    @PostMapping("/deleteWarehouse/{id}")
    public CustomResponse deleteWarehouse(@PathVariable("id") int id, HttpServletRequest request) {
        return warehouserService.deleteLocation(request, id);
    }

    @ApiOperation(value = "查询仓库关联", notes = "传入page以及size")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "请求成功"),
            @ApiResponse(code = 4000, message = "请求失败")
    })
    @PostMapping("/list")
    public CustomResponse list(@RequestParam int page, @RequestParam int size, HttpServletRequest request) {
        return warehouserService.list(page, size, request);
    }
}