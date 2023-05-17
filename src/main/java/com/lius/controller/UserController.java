package com.lius.controller;

import com.lius.common.Result;
import com.lius.common.Utils;
import com.lius.entity.User;
import com.lius.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @PutMapping()
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(value = "id", required = true) Integer id) {
        userService.deleteUserById(id);
    }

    @GetMapping()
    public void selectAllUser() {
        System.out.println(userService.selectAllUser());
    }

    @GetMapping("/{userName}")
    public void selectUserByUserName(@PathVariable(value = "userName", required = true) String userName) {
        System.out.println(userService.selectUserByUserName(userName));
    }


    @GetMapping("/login")
    public Result<Object> login(@RequestBody User user) {
        User u = userService.login(user);
        System.out.println(u);

        if (u == null) return new Result<Object>(false, "登录失败");

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", Utils.generateToken(user));
        return new Result<Object>(true, "登录成功", resultMap);
    }

    @PostMapping("/register")
    public Result<Object> register(@RequestBody User user) {
        User register = userService.register(user);
        if (register == null) return new Result<Object>(false, "注册失败");

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", Utils.generateToken(user));
        return new Result<Object>(true, "注册成功", resultMap);
    }

}
