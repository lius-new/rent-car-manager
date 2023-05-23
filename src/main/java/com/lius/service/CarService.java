package com.lius.service;

import com.lius.entity.Car;
import com.lius.entity.CarShow;
import com.lius.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CarService {

    int insertCar(Car car);

    int updateCar(Car car);

    int deleteCarById(int id);

    List<CarShow> selectAllCar();

    List<CarShow> selectCarByUser(String userName);

    Car selectCarByCarName(String carName);
}
