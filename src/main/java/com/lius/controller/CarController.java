package com.lius.controller;

import com.lius.common.Result;
import com.lius.common.ResultCode;
import com.lius.dao.RentMapper;
import com.lius.entity.Car;
import com.lius.entity.Rent;
import com.lius.entity.User;
import com.lius.service.CarService;
import com.lius.service.RentService;
import com.lius.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;
    @Autowired
    private RentService rentService;

    @PostMapping()
    public Result<Object> insertCar(@RequestBody Car car) {
        if ("".equals(car.getCarName()) || "".equals(car.getUser().getUserName()))
            return new Result<>(false, ResultCode.REQUEST_PARAM_ERROR.getCode(), ResultCode.REQUEST_PARAM_ERROR.getMsg() + ",参数异常");

        User user = userService.selectUserByUserName(car.getUser().getUserName());
        if (user == null)
            return new Result<>(false, ResultCode.DATABASE_OPERATE_FAIL.getCode(), ResultCode.DATABASE_OPERATE_FAIL.getMsg());

        car.setCarUserId(String.valueOf(user.getId()));

        System.out.println(car);

        int i = carService.insertCar(car);
        if (i > 0) {
            return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg());
        }
        return new Result<>(false, ResultCode.DATABASE_OPERATE_FAIL.getCode(), ResultCode.DATABASE_OPERATE_FAIL.getMsg());
    }

    @PutMapping()
    public Object updateCar(@RequestBody Car car) {
        Rent rent = car.getRent();
        // car.getRentId() == null, rent.getId() == null 表明没有租借
        // car.getRentId() != null, rent.getRentUserId() != null 表明修改租借时间
        // car.getRentId() == null rent.getRentUserId() != null 表明新增的租借信息

        if (car.getRentId() != null && rent.getRentUserId() != null) { // 有租借信息，更新租借信息
            int updateCarCount = carService.updateCar(car);
            Integer updateRentCount = rentService.updateRent(car.getRentId(), rent.getRentUserId(), rent.getRentStartDate(), rent.getRentEndDate());
            if (updateCarCount > 0 && updateRentCount > 0)
                return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg());
            else
                return new Result<>(false, ResultCode.DATABASE_OPERATE_FAIL.getCode(), ResultCode.DATABASE_OPERATE_FAIL.getMsg());
        } else if (car.getRentId() == null && rent.getRentUserId() != null) { // 添加新的租借信息
            // 新增车辆信息
            Integer insertRentCount = rentService.insertRent(rent);
            Rent rent1 = rentService.selectCurrentRent(rent.getRentUserId(), rent.getRentCarId());
            System.out.println(rent1);
            car.setRentId(rent1.getId());
            int updateCarCount = carService.updateCar(car);
            if (updateCarCount > 0 && insertRentCount > 0)
                return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg());
            else
                return new Result<>(false, ResultCode.DATABASE_OPERATE_FAIL.getCode(), ResultCode.DATABASE_OPERATE_FAIL.getMsg());
        } else if (car.getRentId() == null && rent.getId() == null) { // 没有租借信息，直接更新车辆信息
            int updateCarCount = carService.updateCar(car);
            if (updateCarCount > 0)
                return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg());
            else
                return new Result<>(false, ResultCode.DATABASE_OPERATE_FAIL.getCode(), ResultCode.DATABASE_OPERATE_FAIL.getMsg());
        } else {
            return new Result<Object>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg());
        }
    }

    @DeleteMapping()
    public Result<Object> deleteCar(@RequestParam Map<String, String> params) {
        // 要先删除 id

        String userName = params.get("userName");
        String cId = params.get("cId");

        if (userName == null || cId == null) { // 请求参数错误
            return new Result<>(false, ResultCode.REQUEST_PARAM_ERROR.getCode(), ResultCode.REQUEST_PARAM_ERROR.getMsg() + ",参数异常");
        }

        // 删除rent中的数据(实际上userName是没有用上的,因为rent表中实际上只有userId,不想麻烦了)
        Integer delete1 = rentService.deleteRent(userName, cId);

        // 查看被删除的数据条数
        List<Rent> rents = rentService.selectByCarId(cId);

        if (rents.size() != 0) // 删除失败的话
            return new Result<>(false, ResultCode.DATABASE_OPERATE_FAIL.getCode(), ResultCode.DATABASE_OPERATE_FAIL.getMsg());

        int i = carService.deleteCarById(cId);

        if (i > 0) {
            return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg());
        }
        return new Result<>(false, ResultCode.DATABASE_OPERATE_FAIL.getCode(), ResultCode.DATABASE_OPERATE_FAIL.getMsg());
    }

    @GetMapping()
    public Result<Object> selectAllCar() {
        return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg(), carService.selectAllCar());
    }

    @GetMapping("/car-user-rent")
    public Result<Object> selectAllCarAndUserAndRent() {
        return new Result<>(true, ResultCode.DATABASE_OPERATE_SUCCESS.getCode(), ResultCode.DATABASE_OPERATE_SUCCESS.getMsg(), carService.selectAllCarAndUserAndRent());
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
