package com.lius.service;

import com.lius.entity.Car;
import com.lius.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CarService {

    int insertCar(Car car);

    int updateCar(Car car);

    User deleteCarById(int id);

    List<User> selectAllCar();

    User selectCarByCarName( String username);
}
