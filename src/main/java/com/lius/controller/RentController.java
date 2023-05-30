package com.lius.controller;

import com.lius.common.Result;
import com.lius.common.ResultCode;
import com.lius.entity.Car;
import com.lius.entity.Rent;
import com.lius.entity.RentSave;
import com.lius.entity.User;
import com.lius.service.CarService;
import com.lius.service.RentService;
import com.lius.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @GetMapping()
    public List<Car> selectAllRent() {
        return rentService.selectAllRent();
    }

    @PostMapping()
    public Result<Object> rentCar(@RequestBody RentSave rentInfo) {
        User user = userService.selectIdByUserName(rentInfo.getUserName());
        if (user == null) return new Result<>(false,ResultCode.RUN_OPERATE_ERROR.getCode(),ResultCode.RUN_OPERATE_ERROR.getMsg());

        // 存储rent数据
        Integer integer = rentService.rentCar(new Rent(user.getId(), rentInfo.getCarId(), rentInfo.getStartTime(), rentInfo.getEndTime()));
        // 存储失败
        if (integer != 1) return new Result<>(false,ResultCode.RUN_OPERATE_ERROR.getCode(),ResultCode.RUN_OPERATE_ERROR.getMsg());

        // 获取存储的rent
        Rent rent = rentService.selectCurrentRent(user.getId(), rentInfo.getCarId());

        // 获取失败
        if (rent == null) return new Result<>(false,ResultCode.RUN_OPERATE_ERROR.getCode(),ResultCode.RUN_OPERATE_ERROR.getMsg());

        // 修改car表格数据
        int i = carService.updateCarRentId(rent.getId(), rentInfo.getCarId());

        // 修改失败
        if (i != 1) return new Result<>(false,ResultCode.RUN_OPERATE_ERROR.getCode(),ResultCode.RUN_OPERATE_ERROR.getMsg());

        return new Result<>(true,ResultCode.RUN_OPERATE_SUCCESS.getCode(),"租借成功");
    }

}
