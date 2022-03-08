package com.bolin.logistics.controller;

import com.bolin.logistics.model.User;
import com.bolin.logistics.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Api("用户相关操作Controller")
@Controller("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="添加用户", notes="添加用户", produces="application/json")
    @PostMapping("/addUser")
    public void addUser(User user , HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        userService.addUser(token , user);
    }
}
