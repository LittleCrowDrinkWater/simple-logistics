package com.bolin.logistics.controller;

import com.bolin.logistics.model.User;
import com.bolin.logistics.service.UserService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api("用户相关操作控制器")
@RestController
@RequestMapping(("/user"))
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户", notes = "添加用户", produces = "application/json")
    @PostMapping("/addUser")
    public CustomResponse addUser(@RequestBody User user , @CookieValue("token") String token) {
        return userService.addUser(token, user);
    }

    @ApiOperation(value = "修改用户", notes = "修改用户", produces = "application/json")
    @PostMapping("/updateUser")
    public CustomResponse updateUser(@RequestBody User user , @CookieValue("token") String token) {
        return userService.updateUserByAdmin(token, user);
    }

    @ApiOperation(value = "用户修改自身信息", notes = "用户修改自身信息", produces = "application/json")
    @PostMapping("/updateUserByUser")
    public CustomResponse updateUserByUser(@RequestBody User user , @CookieValue("token") String token) {
        return userService.updateUserByUser(token, user);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户", produces = "application/json")
    @PostMapping("/deleteUser")
    public CustomResponse deleteUser(@RequestBody Map map , @CookieValue("token") String token) {
        String temp = String.valueOf(map.get("userId"));
        long userId = Long.parseLong(temp);
        return userService.deleteUser(token, userId);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", produces = "application/json")
    @PostMapping("/login")
    public CustomResponse login(@RequestBody Map map , HttpServletResponse response) {
        String telOrEmailOrName = String.valueOf(map.get("telOrEmailOrName"));
        String password = String.valueOf(map.get("password"));
        CustomResponse login = userService.login(telOrEmailOrName, password , response);
        return login;
    }

    @ApiOperation(value = "注销登录", notes = "注销登录", produces = "application/json")
    @GetMapping("/logout")
    public CustomResponse logout(HttpServletRequest request, HttpServletResponse response) {
        return userService.logout(request , response);
    }
}
