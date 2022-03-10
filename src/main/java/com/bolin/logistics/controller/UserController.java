package com.bolin.logistics.controller;

import com.bolin.logistics.model.User;
import com.bolin.logistics.service.UserService;
import com.bolin.logistics.utils.CustomResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(tags="用户相关操作控制器")
@RestController
@RequestMapping(("/user"))
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户", notes = "前端传入User对象时，不需要id和日期，其余字段均需要", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "新增成功"),
            @ApiResponse(code = 4001, message = "新增失败")
    })
    @PostMapping("/addUser")
    public CustomResponse addUser(@RequestBody User user , @CookieValue("token") String token) {
        return userService.addUser(token, user);
    }

    @ApiOperation(value = "客户注册", notes = "前端传入User对象时，需要tel、email、name和password", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2001, message = "新增成功"),
            @ApiResponse(code = 4001, message = "新增失败")
    })
    @PostMapping("/register")
    public CustomResponse register(@RequestBody User user) {
        return userService.register(user);
    }

    @ApiOperation(value = "修改用户", notes = "修改用户时传入用户id和需要修改的字段，无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/updateUser")
    public CustomResponse updateUser(@RequestBody User user , @CookieValue("token") String token) {
        return userService.updateUserByAdmin(token, user);
    }

    @ApiOperation(value = "用户修改自身信息", notes = "修改用户时传入用户id和需要修改的字段，无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/updateUserByUser")
    public CustomResponse updateUserByUser(@RequestBody User user , @CookieValue("token") String token) {
        return userService.updateUserByUser(token, user);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户时，传入\"userId\":userId，后端封装在map里读取", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "删除成功"),
            @ApiResponse(code = 4003, message = "删除失败")
    })
    @PostMapping("/deleteUser")
    public CustomResponse deleteUser(@RequestBody Map map , @CookieValue("token") String token) {
        String temp = String.valueOf(map.get("userId"));
        long userId = Long.parseLong(temp);
        return userService.deleteUser(token, userId);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录时，tel、email或name其中之一和password，\"telOrEmailOrName\":telOrEmailOrName和\"password\":password", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2005, message = "登录成功"),
            @ApiResponse(code = 4000, message = "登录失败")
    })
    @PostMapping("/login")
    public CustomResponse login(@RequestBody Map map , HttpServletResponse response) {
        String telOrEmailOrName = String.valueOf(map.get("telOrEmailOrName"));
        String password = String.valueOf(map.get("password"));
        CustomResponse login = userService.login(telOrEmailOrName, password , response);
        return login;
    }

    @ApiOperation(value = "注销登录", notes = "注销登录", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "请求成功"),
            @ApiResponse(code = 4000, message = "请求失败")
    })
    @GetMapping("/logout")
    public CustomResponse logout(HttpServletRequest request, HttpServletResponse response) {
        return userService.logout(request , response);
    }
}
