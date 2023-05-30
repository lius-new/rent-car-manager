package com.lius.controller;

import com.lius.common.Result;
import com.lius.common.ResultCode;
import com.lius.common.Utils;
import com.lius.entity.User;
import com.lius.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public Result<Object> insertUser(@RequestBody User user) {
        int i = userService.insertUser(user);
        if (i > 0){
            return new Result<>(true,ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg());
        }
        return new Result<>(false,ResultCode.DATABASE_OPERATE_FAIL.getCode(), ResultCode.DATABASE_OPERATE_FAIL.getMsg());

    }

    @PutMapping()
    public Result<Object> updateUser(@RequestBody User user) {
        int i = userService.updateUser(user);
        if (i > 0){
            return new Result<>(true,ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg());
        }
        return new Result<>(false,ResultCode.DATABASE_OPERATE_FAIL.getCode(), ResultCode.DATABASE_OPERATE_FAIL.getMsg());
    }

    @DeleteMapping("/{userName}")
    public Result<Object> deleteUser(@PathVariable(value = "userName", required = true) String userName) {
        int i = userService.deleteUserByUserName(userName);
        if (i > 0){
            return new Result<>(true,ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg());
        }
        return new Result<>(false,ResultCode.DATABASE_OPERATE_FAIL.getCode(), ResultCode.DATABASE_OPERATE_FAIL.getMsg());
    }

    @GetMapping()
    public Result<Object> selectAllUser() {
        List<User> users = userService.selectAllUser();
        return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg(), users);
    }


    @GetMapping("/{userName}")
    public Result<Object> selectUserByUserName(@PathVariable(value = "userName", required = true) String userName) {
        User user = userService.selectUserByUserName(userName);
        return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg(), user);
    }


    @PostMapping("/login")
    public Result<Object> login(@RequestBody User user) {
        User u = userService.login(user);

        if (u == null)
            return new Result<Object>(false, ResultCode.LOGIN_FAIL.getCode(), ResultCode.LOGIN_FAIL.getMsg());
        if (u.getUserStatus() == 1) {
            return new Result<Object>(false, ResultCode.LOGIN_FAIL_STATUS_ERROR.getCode(), ResultCode.LOGIN_FAIL_STATUS_ERROR.getMsg());
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", Utils.generateToken(u));
        resultMap.put("role", u.getUserRole());
        return new Result<Object>(true, ResultCode.LOGIN_SUCCESS.getCode(), ResultCode.LOGIN_SUCCESS.getMsg(), resultMap);
    }

    @PostMapping("/register")
    public Result<Object> register(@RequestBody User user) {
        User register = userService.register(user);
        // 返回register
        if (register == null)
            return new Result<Object>(false, ResultCode.REGISTER_FAIL.getCode(), ResultCode.REGISTER_FAIL.getMsg());

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", Utils.generateToken(register));
        resultMap.put("role", register.getUserRole());
        return new Result<Object>(true, ResultCode.REGISTER_SUCCESS.getCode(), ResultCode.REGISTER_SUCCESS.getMsg(), resultMap);
    }

    @GetMapping("/authLogin")
    public Result<Object> authLogin(HttpServletRequest request, @RequestParam(value = "userRole") String userRole) throws ExceptionAdvice.CheckTokenException {
        String token = request.getHeader("Authorization");
        if (token == null)
            return new Result<>(true, ResultCode.LOGIN_DEAD_STATUS.getCode(), ResultCode.LOGIN_DEAD_STATUS.getMsg());

        User user = Utils.parseToken(token);
        if (!user.getUserRole().equals(userRole)) { // 比较token中和localStorage中的userRole,如果权限被篡改了，那么就返回到未登录状态
            return new Result<>(true, ResultCode.LOGIN_DEAD_STATUS.getCode(), ResultCode.LOGIN_DEAD_STATUS.getMsg());
        }
        boolean b = userService.checkLogin(user); // true: 登录, false: 未登录
        if (b) {
            return new Result<>(true, ResultCode.LOGIN_ACTIVE_STATUS.getCode(), ResultCode.LOGIN_ACTIVE_STATUS.getMsg());
        } else {
            return new Result<>(true, ResultCode.LOGIN_DEAD_STATUS.getCode(), ResultCode.LOGIN_DEAD_STATUS.getMsg());
        }
    }

    @GetMapping("/change-user-role")
    public Result<Object> changeUserRole(@RequestBody User user) {
        //  user 中的id和role不可以为空
        int i = userService.changeUserRole(user);

        if (i > 0) {
            return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), "升级成功");
        }
        return new Result<>(true, ResultCode.DATABASE_OPERATE_FAIL.getCode(), "升级失败");
    }

}
