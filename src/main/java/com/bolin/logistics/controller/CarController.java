package com.bolin.logistics.controller;

import com.bolin.logistics.model.Car;
import com.bolin.logistics.service.CarService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;



@Api(tags="车辆相关控制器")
@RestController
@RequestMapping(("/car"))
public class CarController {
    @Autowired
    private CarService carService;

    @ApiOperation(value = "添加车辆", notes = "添加车辆,传入Car对象，不需要id，其余字段均需要", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "新增成功"),
            @ApiResponse(code = 4001, message = "新增失败")
    })
    @PostMapping("/addCar")
    public CustomResponse addCar(@RequestBody Car car, HttpServletRequest request) {
        return carService.addCar(request, car);
    }

    @ApiOperation(value = "修改车辆", notes = "修改车辆,传入id和需要修改的字段,无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/updateCar")
    public CustomResponse updateCar(@RequestBody Car car, HttpServletRequest request) {
        return carService.updateCar(request, car);
    }

    @ApiOperation(value = "删除车辆", notes = "删除车辆,传入\"id\":id，后端封装在map里读取", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "删除成功"),
            @ApiResponse(code = 4003, message = "删除失败")
    })
    @PostMapping("/deleteCar")
    public CustomResponse deleteCar(HttpServletRequest request , @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        long id = Long.parseLong(temp);
        return carService.deleteCar(request, id);
    }

    @ApiOperation(value = "查询所有车辆，仅管理员可用", notes = "查询所有车辆，仅管理员可用")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "请求成功"),
            @ApiResponse(code = 4000, message = "请求失败")
    })
    @GetMapping("/list")
    public CustomResponse listByType(@RequestParam int page, @RequestParam int size, HttpServletRequest request) {
        return carService.list(page, size, request);
    }

}