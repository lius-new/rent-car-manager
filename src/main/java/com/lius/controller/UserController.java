package com.lius.controller;

import com.lius.common.Result;
import com.lius.common.Utils;
import com.lius.entity.User;
import com.lius.service.UserService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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


    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User u = userService.login(user);

        if (u == null) return new Result<Null>(false, "登录失败");

        String s = Utils.generateToken(user);
        System.out.println("token: " + s);

        //https://juejin.cn/post/6985828884818165797
        Utils.parseToken(s);

        return new Result<Null>(true, "登录成功");
    }
}
