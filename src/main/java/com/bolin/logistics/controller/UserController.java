package com.bolin.logistics.controller;

import com.bolin.logistics.model.User;
import com.bolin.logistics.service.UserService;
import com.bolin.logistics.utils.CustomResponse;
import com.bolin.logistics.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("用户相关操作Controller")
@Controller("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户", notes = "添加用户", produces = "application/json")
    @ResponseBody
    @PostMapping("/addUser")
    public CustomResponse addUser(User user, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("token");
        return userService.addUser(token, user);
    }

    @ApiOperation(value = "修改用户", notes = "修改用户", produces = "application/json")
    @ResponseBody
    @PostMapping("/updateUser")
    public CustomResponse updateUser(User user, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("token");
        return userService.updateUserByAdmin(token, user);
    }

    @ApiOperation(value = "用户修改自身信息", notes = "用户修改自身信息", produces = "application/json")
    @ResponseBody
    @PostMapping("/updateUserByUser")
    public CustomResponse updateUserByUser(User user, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("token");
        return userService.updateUserByUser(token, user);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户", produces = "application/json")
    @ResponseBody
    @PostMapping("/deleteUser")
    public CustomResponse deleteUser(long userId, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("token");
        return userService.deleteUser(token, userId);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", produces = "application/json")
    @ResponseBody
    @PostMapping("/deleteUser")
    public CustomResponse login(String telOrEmailOrName, String password, HttpServletRequest request, HttpServletResponse response) {
        CustomResponse login = userService.login(telOrEmailOrName, password , response);
        return login;
    }

    @ApiOperation(value = "注销登录", notes = "注销登录", produces = "application/json")
    @ResponseBody
    @PostMapping("/deleteUser")
    public CustomResponse logout(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie("token", ""));
        return CustomResponse.success();
    }
}
