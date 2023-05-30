package com.lius.service;

import com.lius.entity.Car;

import java.util.List;

public interface CarService {

    int insertCar(Car car);

    int updateCar(Car car);

    int updateCarAll(Car car);

    int deleteCarById(String id);

    List<Car> selectAllCar();


    List<Car> selectRentCar();

    List<Car> selectAllCarAndUserAndRent();

    List<Car> selectCarByUser(String userName);

    Car selectCarByCarName(String carName);

    int updateCarRentId(Integer rentId,Integer id);
}
