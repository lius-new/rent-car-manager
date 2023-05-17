package com.lius.controller;

import com.lius.entity.User;
import com.lius.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
