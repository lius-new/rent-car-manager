package com.lius.controller;

import com.lius.common.Result;
import com.lius.common.ResultCode;
import com.lius.entity.Car;
import com.lius.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping()
    public int insertCar(@RequestBody Car car) {
        return carService.insertCar(car);
    }

    @PutMapping()
    public int updateCar(@RequestBody Car car) {
        return carService.updateCar(car);
    }

    @DeleteMapping()
    public int deleteCarById(int id) {
        return carService.deleteCarById(id);
    }

    @GetMapping()
    public Result<Object> selectAllCar() {
        return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg(), carService.selectAllCar());
    }

    @GetMapping("/user/{userName}")
    public Result<Object> selectCarByUser(@PathVariable("userName") String userName) {
        return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg(), carService.selectCarByUser(userName));
    }

    @GetMapping("/{carName}")
    public Car selectCarByCarName(@PathVariable("carName") String carName) {
        return carService.selectCarByCarName(carName);
    }
}
