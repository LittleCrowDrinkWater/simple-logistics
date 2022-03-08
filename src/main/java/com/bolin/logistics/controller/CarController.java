package com.bolin.logistics.controller;

import com.bolin.logistics.model.Car;
import com.bolin.logistics.model.Location;
import com.bolin.logistics.service.CarService;
import com.bolin.logistics.service.LocationService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;



@Api("车辆相关控制器")
@RestController
@RequestMapping(("/car"))
public class CarController {
    @Autowired
    private CarService carService;

    @ApiOperation(value = "添加车辆", notes = "添加车辆", produces = "application/json")
    @PostMapping("/addCar")
    public CustomResponse addCar(@RequestBody Car car, @CookieValue("token") String token) {
        return carService.addCar(token, car);
    }

    @ApiOperation(value = "修改车辆", notes = "修改车辆", produces = "application/json")
    @PostMapping("/updateCar")
    public CustomResponse updateCar(@RequestBody Car car, @CookieValue("token") String token) {
        return carService.updateCar(token, car);
    }

    @ApiOperation(value = "删除车辆", notes = "删除车辆", produces = "application/json")
    @PostMapping("/deleteCar")
    public CustomResponse deleteCar(@CookieValue("token") String token , @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        long id = Long.parseLong(temp);
        return carService.deleteCar(token, id);
    }

}