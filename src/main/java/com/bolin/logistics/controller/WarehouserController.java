package com.bolin.logistics.controller;

import com.bolin.logistics.model.Warehouse;
import com.bolin.logistics.service.WarehouserService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Api("仓库地址关联相关控制器")
@RestController
@RequestMapping(("/Warehouse"))
public class WarehouserController {

    @Autowired
    private WarehouserService warehouserService;

    @ApiOperation(value = "添加仓库关联", notes = "添加仓库关联", produces = "application/json")
    @PostMapping("/addWarehouse")
    public CustomResponse addWarehouse(@RequestBody Warehouse warehouse, @CookieValue("token") String token) {
        return warehouserService.addWarehouse(token, warehouse);
    }

    @ApiOperation(value = "修改仓库关联", notes = "修改仓库关联", produces = "application/json")
    @PostMapping("/updateWarehouse")
    public CustomResponse updateWarehouse(@RequestBody Warehouse warehouse, @CookieValue("token") String token) {
        return warehouserService.updateLocation(token, warehouse);
    }

    @ApiOperation(value = "删除仓库关联", notes = "删除仓库关联", produces = "application/json")
    @PostMapping("/deleteWarehouse")
    public CustomResponse deleteWarehouse(@CookieValue("token") String token , @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        int id = Integer.parseInt(temp);
        return warehouserService.deleteLocation(token, id);
    }

}