package com.bolin.logistics.controller;


import com.bolin.logistics.model.Location;
import com.bolin.logistics.service.LocationService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "仓库地址相关控制器")
@RestController
@RequestMapping(("/location"))
public class LocationController {

    @Autowired
    private LocationService locationService;

    @ApiOperation(value = "添加仓库地址", notes = "添加仓库地址,传入Location对象，不需要id，其余字段均需要", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "新增成功"),
            @ApiResponse(code = 4001, message = "新增失败")
    })
    @PostMapping("/addLocation")
    public CustomResponse addLocation(@RequestBody Location location,  HttpServletRequest request) {
        return locationService.addLocation(request, location);
    }

    @ApiOperation(value = "修改仓库地址", notes = "修改仓库地址,传入id和需要修改的字段,无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/updateLocation")
    public CustomResponse updateLocation(@RequestBody Location location, HttpServletRequest request) {
        return locationService.updateLocation(request, location);
    }

    @ApiOperation(value = "删除仓库地址", notes = "删除仓库地址,传入\"id\":id，后端封装在map里读取", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "删除成功"),
            @ApiResponse(code = 4003, message = "删除失败")
    })
    @PostMapping("/deleteLocation")
    public CustomResponse deleteLocation(HttpServletRequest request , @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        int id = Integer.parseInt(temp);
        return locationService.deleteLocation(request, id);
    }

    @ApiOperation(value = "查询所有仓库地址，仅管理员可用", notes = "查询所有仓库地址，仅管理员可用")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "请求成功"),
            @ApiResponse(code = 4000, message = "请求失败")
    })
    @GetMapping("/list")
    public CustomResponse list(@RequestParam int page, @RequestParam int size, HttpServletRequest request) {
        return locationService.list(page, size, request);
    }

}
