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

@Api(tags = "用户相关操作控制器")
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
    public CustomResponse addUser(@RequestBody User user, HttpServletRequest request) {
        return userService.addUser(user, request);
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
    public CustomResponse updateUser(@RequestBody User user, HttpServletRequest request) {
        return userService.updateUserByAdmin(user, request);
    }

    @ApiOperation(value = "用户修改自身信息", notes = "修改用户时传入用户id和需要修改的字段，无需修改的字段传空即可", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2002, message = "更新成功"),
            @ApiResponse(code = 4002, message = "更新失败")
    })
    @PostMapping("/updateUserByUser")
    public CustomResponse updateUserByUser(@RequestBody User user, HttpServletRequest request) {
        return userService.updateUserByUser(user, request);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户,在Get请求中添加参数id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2003, message = "删除成功"),
            @ApiResponse(code = 4003, message = "删除失败")
    })
    @GetMapping("/deleteUser/{id}")
    public CustomResponse deleteUser(@PathVariable("id") long userId, HttpServletRequest request) {
        return userService.deleteUser(userId, request);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录时，tel、email或name其中之一和password，\"telOrEmailOrName\":telOrEmailOrName和\"password\":password", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2005, message = "登录成功"),
            @ApiResponse(code = 4000, message = "登录失败")
    })
    @PostMapping("/login")
    public CustomResponse login(@RequestBody Map map, HttpServletResponse response) {
        String telOrEmailOrName = String.valueOf(map.get("telOrEmailOrName"));
        String password = String.valueOf(map.get("password"));
        CustomResponse login = userService.login(telOrEmailOrName, password, response);
        return login;
    }

    @ApiOperation(value = "注销登录", notes = "注销登录", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "请求成功"),
            @ApiResponse(code = 4000, message = "请求失败")
    })
    @GetMapping("/logout")
    public CustomResponse logout(HttpServletRequest request, HttpServletResponse response) {
        return userService.logout(request, response);
    }

    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "请求成功"),
            @ApiResponse(code = 4000, message = "请求失败")
    })
    @GetMapping("/list")
    public CustomResponse<User> list(@RequestParam int page, @RequestParam int size, HttpServletRequest request) {
        return userService.list(page, size, request);
    }

    @ApiOperation(value = "按照类型查询用户", notes = "按照类型查询用户")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "请求成功"),
            @ApiResponse(code = 4000, message = "请求失败")
    })
    @GetMapping("/listByType")
    public CustomResponse<User> listByType(@RequestParam int page, @RequestParam int size, @RequestParam int type, HttpServletRequest request) {
        return userService.list(type, page, size, request);
    }

    @ApiOperation(value = "根据cookie查询", notes = "根据cookie查询当前用户，直接请求该接口，token正确返回相应数据")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "请求成功"),
            @ApiResponse(code = 4000, message = "请求失败")
    })
    @GetMapping("/getUserByToken")
    public CustomResponse<User> getUserByToken(HttpServletRequest request) {
        return userService.getUserByToken(request);
    }
}
