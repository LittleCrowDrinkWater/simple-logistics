package com.bolin.logistics.controller;


import com.bolin.logistics.model.Location;
import com.bolin.logistics.service.LocationService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Api("仓库地址相关控制器")
@RestController
@RequestMapping(("/location"))
public class LocationController {

    @Autowired
    private LocationService locationService;

    @ApiOperation(value = "添加仓库地址", notes = "添加仓库地址", produces = "application/json")
    @PostMapping("/addLocation")
    public CustomResponse addLocation(@RequestBody Location location, @CookieValue("token") String token) {
        return locationService.addLocation(token, location);
    }

    @ApiOperation(value = "修改仓库地址", notes = "修改仓库地址", produces = "application/json")
    @PostMapping("/updateLocation")
    public CustomResponse updateLocation(@RequestBody Location location, @CookieValue("token") String token) {
        return locationService.updateLocation(token, location);
    }

    @ApiOperation(value = "删除仓库地址", notes = "删除仓库地址", produces = "application/json")
    @PostMapping("/deleteLocation")
    public CustomResponse deleteLocation(@CookieValue("token") String token , @RequestBody Map map) {
        String temp = String.valueOf(map.get("id"));
        int id = Integer.parseInt(temp);
        return locationService.deleteLocation(token, id);
    }

}
